package de.senatov.reservatio.view;



import de.senatov.reservatio.db.ScheduleEntity;
import de.senatov.reservatio.db.ScheduleService;
import de.senatov.reservatio.utl.ScheduleRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;



@Configuration
@ManagedBean
@ViewScoped
@Slf4j
public class ScheduleView implements Serializable {

	private static final String S_MINUTE_DELTA_S = "Day delta: %s,  Minute delta: %s";
	@Serial
	private static final long serialVersionUID = -2637195560425203881L;

	private final ScheduleModel eventModel = new DefaultScheduleModel();
	@Autowired
	ScheduleRecordMapper mapper;
	private ScheduleEvent event = new DefaultScheduleEvent();

	@Autowired
	private ScheduleService scheduleService;


	@PostConstruct
	public void init() {

		mapper.init();
		for (Object value : mapper.getSheduleMaps()) {
			mapper.extractValue(value);
			eventModel.addEvent(DefaultScheduleEvent.builder()
					.title(mapper.getTitle())
					.startDate(mapper.getStartDate())
					.endDate(mapper.getEndDate())
					.description(mapper.getDescription())
					.groupId(mapper.getGroupId())
					.id(mapper.getId())
					.editable(mapper.getIsEditable())
					.styleClass(mapper.getStyle())
					//.url(mapper.getUrl())  - Don't use it! This parameter already uised by PrimeFaces Schedule Controller's Event Editor.
					.build());
		}
	}


	public ScheduleModel getEventModel() {

		log.debug("getEventModel() = {}", eventModel);
		return eventModel;
	}


	public ScheduleEvent getScEvent () {

		log.debug("getScEvent() = {}", event);
		return event;
	}


	

	public void setShEvent (ScheduleEvent event) {

		log.debug("setShEvent() = {}", event);
		this.event = event;
	}


	public void addShEvent () {

		log.debug("addShEvent() = {}", event);
		//FixMe: WTF?
		DefaultScheduleEvent event = new DefaultScheduleEvent();
		if (event.getId() == null) {
			eventModel.addEvent(event);
			ScheduleEntity scheduleEntity = mapper.mapEvent(event);
			scheduleService.updateSchedule(scheduleEntity);
		} else {
			eventModel.updateEvent(event);
			scheduleService.updateSchedule(mapper.mapEvent(event));
		}
		event = new DefaultScheduleEvent();
	}


	public void onShEventSelect (SelectEvent selectEvent) {

		log.debug("onShEventSelect() = {}", selectEvent);
		event = (ScheduleEvent) selectEvent.getObject();
	}


	public void onDateSelect(SelectEvent selectEvent) {

		event = DefaultScheduleEvent.builder()
				.title("")
				.startDate((LocalDateTime) selectEvent.getObject())
				.endDate((LocalDateTime) selectEvent.getObject())
				.build();
	}



	public void onShEventMove (ScheduleEntryMoveEvent event) {

		log.debug("onShEventMove()");
		FacesMessage message = new FacesMessage(SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
		addMessage(message);
		Long scheduleId = Long.valueOf(event.getScheduleEvent().getId());
		ScheduleEntity sheduleMaps = scheduleService.getSchedule(scheduleId).get();
		scheduleService.updateSchedule(sheduleMaps);
	}


	public void onShEventResize (ScheduleEntryResizeEvent event) {
		log.debug("onShEventResize()");
		FacesMessage message = new FacesMessage(SEVERITY_INFO, "Event resized","Day delta:" + event.getDayDeltaEnd() + ", Minute delta:" + event.getMinuteDeltaEnd());
		addMessage(message);
	}


	private void addMessage(FacesMessage message) {

		log.debug("addMessage()");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
