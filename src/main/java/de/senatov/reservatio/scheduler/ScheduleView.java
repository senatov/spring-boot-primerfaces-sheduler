package de.senatov.reservatio.scheduler;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;



@ToString
@ManagedBean
@ViewScoped
@Slf4j
public class ScheduleView implements Serializable {

	private static final String MINUTE_DELTA = ", Minute delta:";
	private static final String DAY_DELTA = "Day delta:";
	private static final long serialVersionUID = 2653991725372403680L;
	public static final String AUD1 = "Аудитория 1";
	public static final String CLO = "Туалет на втором";
	public static final String AUD4 = "Аудитория 4";
	public static final String GS1 = "Переговорная 103";
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();



	@PostConstruct
	public void init() {

		log.debug("init()");
		eventModel = new DefaultScheduleModel();
		eventModel.addEvent(new DefaultScheduleEvent(AUD1, previousDay8Pm(), previousDay11Pm()));
		eventModel.addEvent(new DefaultScheduleEvent(CLO, today1Pm(), today6Pm()));
		eventModel.addEvent(new DefaultScheduleEvent(AUD4, nextDay9Am(), nextDay11Am()));
		eventModel.addEvent(new DefaultScheduleEvent(GS1, theDayAfter3Pm(), fourDaysLater3pm()));
	}



	public ScheduleModel getEventModel() {

		return eventModel;
	}



	private Date previousDay8Pm() {

		log.debug("previousDay8Pm()");
		LocalDateTime dt = LocalDateTime
				.now()
				.minusDays(1L)
				.withHour(8)
				.withMinute(0);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}



	private Date previousDay11Pm() {

		log.debug("previousDay11Pm()");
		LocalDateTime dt = LocalDateTime
				.now()
				.withHour(11)
				.withMinute(21);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}



	private Date today1Pm() {

		log.debug("today1Pm()");
		LocalDateTime dt = LocalDateTime
				.now()
				.withHour(13)
				.withMinute(0);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}



	private Date theDayAfter3Pm() {

		log.debug("theDayAfter3Pm()");
		LocalDateTime dt = LocalDateTime
				.now()
				.plusDays(1L)
				.withHour(13)
				.withMinute(0);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}



	private Date today6Pm() {

		log.debug("today6Pm()");
		LocalDateTime dt = LocalDateTime
				.now()
				.plusDays(2L)
				.withHour(6)
				.withMinute(0);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());

	}



	private Date nextDay9Am() {

		log.debug("nextDay9Am()");
		LocalDateTime dt = LocalDateTime
				.now()
				.plusDays(1L)
				.withHour(9)
				.withMinute(0);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}



	private Date nextDay11Am() {

		log.debug("nextDay11Am()");
		LocalDateTime dt = LocalDateTime
				.now()
				.plusDays(1L)
				.withHour(11)
				.withMinute(0);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());

	}



	private Date fourDaysLater3pm() {

		log.debug("fourDaysLater3pm()");
		LocalDateTime dt = LocalDateTime
				.now()
				.plusDays(4L)
				.withHour(15)
				.withMinute(0);
		return Date.from(dt
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}



	public ScheduleEvent getEvent() {

		return event;
	}



	public void setEvent(ScheduleEvent event) {

		this.event = event;
	}



	public void addEvent() {

		if (event.getId() == null) {
			eventModel.addEvent(event);
		}
		else {
			eventModel.updateEvent(event);
		}
		event = new DefaultScheduleEvent();
	}



	public void onEventSelect(SelectEvent selectEvent) {

		event = (ScheduleEvent) selectEvent.getObject();
	}



	public void onDateSelect(SelectEvent selectEvent) {

		log.debug("onDateSelect()");
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}



	public void onEventMove(ScheduleEntryMoveEvent event) {

		log.debug("onEventMove()");
		String detail = DAY_DELTA + event.getDayDelta() + MINUTE_DELTA + event.getMinuteDelta();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", detail);
		addMessage(message);
	}



	public void onEventResize(ScheduleEntryResizeEvent event) {

		log.debug("onEventResize()");
		String detail = DAY_DELTA + event.getDayDelta() + MINUTE_DELTA + event.getMinuteDelta();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", detail);
		addMessage(message);
	}



	private void addMessage(FacesMessage message) {

		log.debug("addMessage()");
		FacesContext
				.getCurrentInstance()
				.addMessage(null, message);
	}

}
