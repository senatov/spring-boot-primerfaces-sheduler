package de.senatov.reservatio.utl;



import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.model.ScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedCaseInsensitiveMap;

import de.senatov.reservatio.db.ScheduleEntity;
import de.senatov.reservatio.db.ScheduleService;
import de.senatov.reservatio.db.UserEntity;
import de.senatov.reservatio.db.UserService;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;



@Configuration
@Data
@Slf4j
public class ScheduleRecordMapper {

	@Autowired
	private UserService userService;

    public static final String DATE_S_ERR_MSG = """
                                                Wrong Event'%s': end Date before start Date!
                                                startDate = %s
                                                startDate = %s""";

    @Autowired
    private ScheduleService scheduleService;

    private LinkedCaseInsensitiveMap map;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String groupId;
    private Long id;
    private Boolean isEditable;
    private String style;
    private String url;
    private String schedule_id;
    private List<ScheduleEntity> sheduleMaps;
    private UserEntity userEntity;



    public void init() throws Exception {

        sheduleMaps = scheduleService.getAllSchedules();
    }



    public void extractValue(ScheduleEntity scheduleEntity) throws Exception {

        description = scheduleEntity.getDescription();
        title = scheduleEntity.getTitle();
        startDate = scheduleEntity.getStartDate();
        endDate = scheduleEntity.getEndDate();
        breakIfDateAfterBefore(startDate, endDate, description);
        groupId = scheduleEntity.getGroupId();
        schedule_id = scheduleEntity.getScheduleId();
        id = scheduleEntity.getId();
        isEditable = scheduleEntity.getIsEditable();
        style = scheduleEntity.getStyleClass();
        url = scheduleEntity.getUrl();
        //todo: where is real user-id?
        userEntity = scheduleEntity.getUserEntity();
    }



    public void breakIfDateAfterBefore(LocalDateTime bdate, LocalDateTime edate, String descr) {

        if (bdate.isAfter(edate)) {
            throw new DateTimeException(format(DATE_S_ERR_MSG, descr, bdate, edate));
        }
    }



    public ScheduleEntity mapEvent(ScheduleEvent event) throws Exception {

        ScheduleEntity ret = new ScheduleEntity();
        ret.setDescription(isBlank(event.getDescription()) ? event.getTitle() : event.getDescription());
        ret.setEndDate(event.getEndDate());
        ret.setGroupId(event.getGroupId());
        ret.setId(Long.parseLong(event.getId()));
        ret.setIsEditable(Boolean.TRUE);
        ret.setScheduleId(event.getId());
        ret.setStartDate(event.getStartDate());
        ret.setStyleClass(event.getStyleClass());
        ret.setTitle(event.getTitle());
        ret.setUrl(event.getUrl());
        ret.setUserEntity(userEntity==null ? getFirstEntity() : userEntity);
        return ret;
    }



	private UserEntity getFirstEntity() throws Exception{
		return userService.getAllUsers().get(0);
	}



	public ScheduleEntity mapEvent(ScheduleEntryMoveEvent moveEvent) throws Exception {

        ScheduleEntity ret = new ScheduleEntity();
        ScheduleEvent event = moveEvent.getScheduleEvent();
        ret.setDescription(event.getDescription());
        ret.setEndDate(event.getEndDate());
        ret.setGroupId(event.getGroupId());
        ret.setId(Long.parseLong(event.getId()));
        ret.setIsEditable(Boolean.TRUE);
        ret.setStartDate(event.getStartDate());
        ret.setScheduleId(String.valueOf(scheduleService.findMaxSheduleId() + 1));
        ret.setStyleClass(event.getStyleClass());
        ret.setTitle(event.getTitle());
        ret.setUrl(event.getUrl());
        ret.setUserEntity(getUserEntity());
        return ret;
    }



    public String getCurrentUser() {

        String ret = "nobody";
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        return authentication.getName();
    }

}
