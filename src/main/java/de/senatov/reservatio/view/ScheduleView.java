package de.senatov.reservatio.view;



import lombok.Getter;
import lombok.Setter;
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

import static javax.faces.application.FacesMessage.SEVERITY_INFO;



@ManagedBean
@ViewScoped
@Slf4j
@Getter
@Setter
@ToString
public class ScheduleView implements Serializable {

	public static final String S_MINUTE_DELTA_S = "Day delta: %s,  Minute delta: %s";
	private static final long serialVersionUID = -2637195560425203881L;
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();



	@PostConstruct
	public void init() {

		log.debug("init()");
		eventModel = new DefaultScheduleModel();
		eventModel.addEvent(DefaultScheduleEvent.builder()
		                                        .title("Room 123")
		                                        .startDate(previousDay8Pm())
		                                        .endDate(previousDay11Pm())
		                                        .url("https://github.com/primefaces/primefaces/pull/5338")
		                                        .build());
		eventModel.addEvent(DefaultScheduleEvent.builder()
		                                        .title("WXC Lab 2")
		                                        .startDate(today1Pm())
		                                        .endDate(today6Pm())
		                                        .description("some description of meeting")
		                                        .build());
		eventModel.addEvent(DefaultScheduleEvent.builder()
		                                        .title("Kantine")
		                                        .startDate(nextDay9Am())
		                                        .endDate(nextDay11Am())
		                                        .description("some description 123")
		                                        .build());
		eventModel.addEvent(DefaultScheduleEvent.builder()
		                                        .title("Meeting Room 34")
		                                        .startDate(in2DaysAfter10Pm())
		                                        .endDate(fourDaysLater3pm())
		                                        .description("some description XYZ")
		                                        .build());
	}



	private LocalDateTime previousDay8Pm() {

		return LocalDateTime.now()
		                    .minusDays(1)
		                    .withHour(8);
	}



	private LocalDateTime previousDay11Pm() {

		return LocalDateTime.now()
		                    .minusDays(1)
		                    .withHour(11)
		                    .withMinute(35);
	}



	private LocalDateTime today1Pm() {

		return LocalDateTime.now()
		                    .withHour(13);
	}



	private LocalDateTime in2DaysAfter10Pm() {

		return LocalDateTime.now().plusDays(2)
		                    .withHour(10);
	}



	private LocalDateTime today6Pm() {

		return LocalDateTime.now()
		                    .withHour(18);
	}



	private LocalDateTime nextDay9Am() {

		return LocalDateTime.now()
		                    .plusDays(1)
		                    .withHour(9)
		                    .withMinute(15);
	}



	private LocalDateTime nextDay11Am() {

		return LocalDateTime.now()
		                    .plusDays(1)
		                    .withHour(11)
		                    .withMinute(30);
	}



	private LocalDateTime fourDaysLater3pm() {

		return LocalDateTime.now().plusDays(2)
		                    .withHour(15);
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

		event = DefaultScheduleEvent.builder()
		                            .title("")
		                            .startDate((LocalDateTime) selectEvent.getObject())
		                            .endDate((LocalDateTime) selectEvent.getObject())
		                            .build();
	}



	public void onEventMove(ScheduleEntryMoveEvent event) {

		String strMsg = String.format(S_MINUTE_DELTA_S, event.getDayDelta(), event.getMinuteDelta());
		FacesMessage message = new FacesMessage(SEVERITY_INFO, "Event moved", strMsg);
		addMessage(message);
	}



	public void onEventResize(ScheduleEntryResizeEvent event) {

		String strMsg = String.format(S_MINUTE_DELTA_S, event.getDayDeltaEnd(), event.getMinuteDeltaEnd());
		FacesMessage message = new FacesMessage(SEVERITY_INFO, "Event resized", strMsg);
		addMessage(message);
	}



	private void addMessage(FacesMessage message) {

		FacesContext.getCurrentInstance()
		            .addMessage(null, message);
	}

}
