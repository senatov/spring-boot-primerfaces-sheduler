package de.senatov.reservatio.schedu;



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
import java.util.Calendar;
import java.util.Date;
//import org.



@ManagedBean
@ViewScoped
@Slf4j
public class ScheduleView implements Serializable {

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();



	@PostConstruct
	public void init() {

		log.debug("init()");
		eventModel = new DefaultScheduleModel();
		eventModel.addEvent(new DefaultScheduleEvent("Аудитория 1", previousDay8Pm(), previousDay11Pm()));
		eventModel.addEvent(new DefaultScheduleEvent("Туалет на втором", today1Pm(), today6Pm()));
		eventModel.addEvent(new DefaultScheduleEvent("Аудитория 4", nextDay9Am(), nextDay11Am()));
		eventModel.addEvent(new DefaultScheduleEvent("Переговорная 103", theDayAfter3Pm(), fourDaysLater3pm()));
	}



	public Date getRandomDate(Date base) {

		log.debug("getRandomDate()");
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30D)) + 1);    //set random day of month
		return date.getTime();
	}



	public Date getInitialDate() {

		log.debug("getInitialDate()");
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
		return calendar.getTime();
	}



	public ScheduleModel getEventModel() {

		return eventModel;
	}



	private Calendar today() {

		log.debug("today()");
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
		return calendar;
	}



	private Date previousDay8Pm() {

		log.debug("previousDay8Pm()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 8);
		return t.getTime();
	}



	private Date previousDay11Pm() {

		log.debug("previousDay11Pm()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 11);
		return t.getTime();
	}



	private Date today1Pm() {

		log.debug("today1Pm()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 1);
		return t.getTime();
	}



	private Date theDayAfter3Pm() {

		log.debug("theDayAfter3Pm()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 3);
		return t.getTime();
	}



	private Date today6Pm() {

		log.debug("today6Pm()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 6);
		return t.getTime();
	}



	private Date nextDay9Am() {

		log.debug("nextDay9Am()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 9);
		return t.getTime();
	}



	private Date nextDay11Am() {

		log.debug("nextDay11Am()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 11);
		return t.getTime();
	}



	private Date fourDaysLater3pm() {

		log.debug("fourDaysLater3pm()");
		Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
		t.set(Calendar.HOUR, 3);
		return t.getTime();
	}



	public ScheduleEvent getEvent() {

		log.debug("getEvent()" + event);
		return event;
	}



	public void setEvent(ScheduleEvent event) {

		log.debug("setEvent()" + event);
		this.event = event;
	}



	public void addEvent() {

		log.debug("addEvent()" + event);
		if (event.getId() == null) {
			eventModel.addEvent(event);
		}
		else {
			eventModel.updateEvent(event);
		}
		event = new DefaultScheduleEvent();
	}



	public void onEventSelect(SelectEvent selectEvent) {

		log.debug("onEventSelect()" + selectEvent);
		event = (ScheduleEvent) selectEvent.getObject();
	}



	public void onDateSelect(SelectEvent selectEvent) {

		log.debug("onDateSelect()");
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}



	public void onEventMove(ScheduleEntryMoveEvent event) {

		log.debug("onEventMove()");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
		addMessage(message);
	}



	public void onEventResize(ScheduleEntryResizeEvent event) {

		log.debug("onEventResize()");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
		addMessage(message);
	}



	private void addMessage(FacesMessage message) {

		log.debug("addMessage()");
		FacesContext
				.getCurrentInstance()
				.addMessage(null, message);
	}

}
