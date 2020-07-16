package de.senatov.reservatio.view;



import de.senatov.reservatio.utl.ScheduleRecordMapper;
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
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;



@Component
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

		eventModel = new DefaultScheduleModel();
		ScheduleRecordMapper sheduleMaps = new ScheduleRecordMapper();
		for (Object value : sheduleMaps.getSheduleMaps()) {
			sheduleMaps.extractVal(value);
			eventModel.addEvent(DefaultScheduleEvent.builder()
			                                        .title(sheduleMaps.getTitle())
			                                        .startDate(sheduleMaps.getStartDate())
			                                        .endDate(sheduleMaps.getEndDate())
			                                        .description(sheduleMaps.getDescription())
			                                        .groupId(sheduleMaps.getGroupId())
			                                        .id(sheduleMaps.getId())
			                                        .editable(sheduleMaps.getIsEditable())
			                                        .styleClass(sheduleMaps.getStyle())
			                                        .url(sheduleMaps.getUrl())
			                                        .build());
		}
	}



	public ScheduleEvent getEvent() {

		log.debug("getEvent() = {}", event);
		return event;
	}



	public void setEvent(ScheduleEvent event) {

		log.debug("getEvent() = {}", event);
		this.event = event;
	}



	public void addEvent() {

		log.debug("getEvent() = {}", event);
		if (event.getId() == null) {
			eventModel.addEvent(event);
		}
		else {
			eventModel.updateEvent(event);
		}
		event = new DefaultScheduleEvent();
	}



	public void onEventSelect(SelectEvent selectEvent) {

		log.debug("onEventSelect() = {}", selectEvent);
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

