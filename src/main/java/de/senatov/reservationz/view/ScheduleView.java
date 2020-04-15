package de.senatov.reservationz.view;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;



@Slf4j
@ToString
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    public static final String MINUTE_DELTA = ", Minute delta:";
    private static final long serialVersionUID = 2653991725372403680L;
    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    @Autowired
    private Logger LOG;



    @PostConstruct
    public void init() {

        LOG.debug("init()");
        eventModel = new DefaultScheduleModel();
        eventModel.addEvent(new DefaultScheduleEvent("Аудитория 1", previousDay8Pm(), previousDay11Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Туалет на втором", today1Pm(), today6Pm()));
        eventModel.addEvent(new DefaultScheduleEvent("Аудитория 4", nextDay9Am(), nextDay11Am()));
        eventModel.addEvent(new DefaultScheduleEvent("Переговорная 103", theDayAfter3Pm(), fourDaysLater3pm()));
    }



    public ScheduleModel getEventModel() {

        return eventModel;
    }



    private Date previousDay8Pm() {

        LOG.debug("previousDay8Pm()");
        LocalDateTime dt = LocalDateTime.now().minusDays(1L).withHour(8).withMinute(0);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }



    private Date previousDay11Pm() {

        LOG.debug("previousDay11Pm()");
        LocalDateTime dt = LocalDateTime.now().withHour(11).withMinute(21);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }



    private Date today1Pm() {

        LOG.debug("today1Pm()");
        LocalDateTime dt = LocalDateTime.now().withHour(13).withMinute(0);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }



    private Date theDayAfter3Pm() {

        LOG.debug("theDayAfter3Pm()");
        LocalDateTime dt = LocalDateTime.now().plusDays(1L).withHour(13).withMinute(0);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }



    private Date today6Pm() {

        LOG.debug("today6Pm()");
        LocalDateTime dt = LocalDateTime.now().plusDays(2L).withHour(6).withMinute(0);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());

    }



    private Date nextDay9Am() {

        LOG.debug("nextDay9Am()");
        LocalDateTime dt = LocalDateTime.now().plusDays(1L).withHour(9).withMinute(0);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }



    private Date nextDay11Am() {

        LOG.debug("nextDay11Am()");
        LocalDateTime dt = LocalDateTime.now().plusDays(1L).withHour(11).withMinute(0);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());

    }



    private Date fourDaysLater3pm() {

        LOG.debug("fourDaysLater3pm()");
        LocalDateTime dt = LocalDateTime.now().plusDays(4L).withHour(15).withMinute(0);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }



    public ScheduleEvent getEvent() {

        LOG.debug("getEvent()" + event);
        return event;
    }



    public void setEvent(ScheduleEvent event) {

        LOG.debug("setEvent()" + event);
        this.event = event;
    }



    public void addEvent() {

        LOG.debug("addEvent()" + event);
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }
        event = new DefaultScheduleEvent();
    }



    public void onEventSelect(SelectEvent selectEvent) {

        LOG.debug("onEventSelect()" + selectEvent);
        event = (ScheduleEvent) selectEvent.getObject();
    }



    public void onDateSelect(SelectEvent selectEvent) {

        LOG.debug("onDateSelect()");
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }



    public void onEventMove(ScheduleEntryMoveEvent event) {

        LOG.debug("onEventMove()");
        String detail = "Day delta:" + event.getDayDelta() + MINUTE_DELTA + event.getMinuteDelta();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", detail);
        addMessage(message);
    }



    public void onEventResize(ScheduleEntryResizeEvent event) {

        LOG.debug("onEventResize()");
        String detail = "Day delta:" + event.getDayDelta() + MINUTE_DELTA + event.getMinuteDelta();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", detail);
        addMessage(message);
    }



    private void addMessage(FacesMessage message) {

        LOG.debug("addMessage()");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
