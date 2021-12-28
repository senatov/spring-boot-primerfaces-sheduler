package de.senatov.reservatio.view;


import java.io.Serial;
import java.io.Serializable;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleDisplayMode;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import de.senatov.reservatio.db.ScheduleEntity;
import de.senatov.reservatio.service.ScheduleService;
import de.senatov.reservatio.service.UserService;
import de.senatov.reservatio.ScheduleRecordMapper;

import static java.lang.String.format;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.apache.commons.lang3.StringUtils.isBlank;


@Configuration
@ManagedBean
@ViewScoped
@Slf4j
public class ScheduleView implements Serializable {

    private static final String S_MINUTE_DELTA_S = "Day delta: %s,  Minute delta: %s";
    @Serial
    private static final long serialVersionUID = -2637195560425203881L;
    public static final String DELTAS = "%s event deltas: Day: %s, Hour: %s, Minutes: %s";

    private final ScheduleModel eventModel = new DefaultScheduleModel();
    @Autowired
    ScheduleRecordMapper mapper;
    private DefaultScheduleEvent event = new DefaultScheduleEvent();

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserService user;


    @PostConstruct
    public void init() throws Exception {

        mapper.init();
        log.debug("init() = {}", mapper.getSheduleMaps());
        for (ScheduleEntity value : mapper.getSheduleMaps()) {
            mapper.extractValue(value);
            eventModel.addEvent(DefaultScheduleEvent.builder()
                    .title(mapper.getTitle())
                    .startDate(mapper.getStartDate())
                    .endDate(mapper.getEndDate())
                    .description(mapper.getDescription())
                    .groupId(mapper.getGroupId())
                    .id(mapper.getSchedule_id())
                    .editable(mapper.getIsEditable())
                    .styleClass(mapper.getStyle())
                    .url(mapper.getUrl())
                    .build());
        }
    }


    public ScheduleModel getEventModel() {

        log.debug("getEventModel() = {}", eventModel);
        return eventModel;
    }


    public ScheduleEvent getEvent() {

        log.debug("getEvent() = {}", event);
        return event;
    }


    public void setEvent(DefaultScheduleEvent scheduleEvent) throws Exception {

        log.debug("setEvent() = {}", scheduleEvent);
        event = scheduleEvent;
    }


    public void addOrUpdateEvent() throws Exception {

        log.debug("addOrUpdateEvent() = {}", event.toString());
        mapper.breakIfDateAfterBefore(event.getStartDate(), event.getEndDate(), event.getDescription());
        if (isBlank(event.getId())) {
            createNewEntry();
        } else {
            moveEntryOnNewPlace();
        }
        event = new DefaultScheduleEvent();
    }


    public void onEventSelect(SelectEvent selectEvent) throws Exception {

        event = (DefaultScheduleEvent) selectEvent.getObject();
        log.debug("onEventSelect() = {}", event);
    }


    public void onEventDelete() throws Exception {

        log.debug("deleteEvent() = {}", event);
        eventModel.updateEvent(event);
        scheduleService.deleteSchedule(mapper.mapEvent(event));
        eventModel.deleteEvent(event);
    }


    public void onDateDblSelect(ScheduleEntryMoveEvent scheduleEntryMoveEvent) throws Exception {

        log.debug("onDateDblSelect() = {}", scheduleEntryMoveEvent);
    }


    public void onDateSelect(SelectEvent selectEvent) throws Exception {

        log.debug("onDateSelect()");
        event = DefaultScheduleEvent.builder()
                .id("")
                .title("")
                .description("")
                .groupId("")
                .draggable(Boolean.TRUE)
                .resizable(Boolean.TRUE)
                .startDate((LocalDateTime) selectEvent.getObject())
                .endDate((LocalDateTime) selectEvent.getObject())
                .display(ScheduleDisplayMode.LIST_ITEM)
                .build();
    }


    public void onEventMove(ScheduleEntryMoveEvent scheduleEntryMoveEvent) throws Exception {

        log.debug("onShEventMove()");
        Duration duratio = getDuration(scheduleEntryMoveEvent, "move");
        event = (DefaultScheduleEvent) scheduleEntryMoveEvent.getScheduleEvent();
        addOrUpdateEvent();
    }


    public void onEventResize(DefaultScheduleEvent scheduleEvent) throws Exception {

        log.debug("onShEventResize()");
        Duration duratio = getDuration(scheduleEvent, "Resize");
        event = scheduleEvent;
        addOrUpdateEvent();
    }


    private void addMessage(FacesMessage message) throws Exception {

        log.debug("addMessage()");
        FacesContext.getCurrentInstance()
                .addMessage(null, message);
    }


    private Duration getDuration(Object event, String opName) throws Exception {

        Duration duratio;
        if (event instanceof ScheduleEntryMoveEvent) {
            duratio = ((ScheduleEntryMoveEvent) event).getDeltaAsDuration();
        } else {
            duratio = ((ScheduleEntryResizeEvent) event).getDeltaEndAsDuration();
        }
        String msg = format(DELTAS, opName, duratio.toDays(), duratio.toHours() % 24L, duratio.toMinutes() % (24L * 60L));
        FacesMessage message = new FacesMessage(SEVERITY_INFO, "Event moved", msg);
        addMessage(message);
        return duratio;
    }


    private void moveEntryOnNewPlace() throws Exception {

        eventModel.updateEvent(event);
        scheduleService.updateSchedule(event);
    }


    private void createNewEntry() throws Exception {

        eventModel.addEvent(event);
        ScheduleEntity scheduleEntity = mapper.mapEvent(event);
        scheduleService.saveSchedule(scheduleEntity);
    }

}
