package de.senatov.reservatio.utl;



import de.senatov.reservatio.db.ScheduleEntity;
import de.senatov.reservatio.db.UserEntity;
import de.senatov.reservatio.db.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.isBlank;


@Configuration
@Data
@Slf4j
public class ScheduleRecordMapper {

    public static final String DATE_S_ERR_MSG = """ 
            Wrong Event'%s': end Date before start Date! 
            startDate = %s
            startDate = %s""";

    private static String SELECT_ALL_FROM_VIEW = """
            select s.schedule_id,
            u.id,
            u.e_mail,
            u.first_name,
            u.last_name,
            u.user_name,
            s.description,
            s.end_date,
            s.group_id,
            s.is_editable,
            s.schedule_id,
            s.start_date,
            s.style_class,
            s.title,
            s.url
            FROM sc_schedule s, sc_user u
            where s.user_name_id = u.id ORDER BY s.schedule_id
            """;

    @Autowired
    UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private LinkedCaseInsensitiveMap map;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private String groupId;
    private String id;
    private Boolean isEditable;
    private String style;
    private String url;

    private List<Map<String, Object>> sheduleMaps;


    public void init() {

        sheduleMaps = jdbcTemplate.queryForList(SELECT_ALL_FROM_VIEW);
    }


    public void extractVal(Object object) {

        map = (LinkedCaseInsensitiveMap) object;
        description = valueOf(map.get("description"));
        title = valueOf(map.get("title"));
        startDate = ((Timestamp) map.get("start_date")).toLocalDateTime();
        endDate = ((Timestamp) map.get("end_date")).toLocalDateTime();
        if (startDate.isAfter(endDate)) {
            throw new DateTimeException(format(DATE_S_ERR_MSG, description, startDate, endDate));
        }
        groupId = valueOf(map.get("group_id"));
        id = valueOf(map.get("id"));
        isEditable = (Boolean) map.get("is_editable");
        style = valueOf(map.get("style_class"));
        url = valueOf(map.get("url"));
    }


    public ScheduleEntity mapEvent(DefaultScheduleEvent event) {

        ScheduleEntity ret = new ScheduleEntity();
        ret.setDescription(getDescription(event));
        ret.setEndDate(event.getEndDate());
        ret.setGroupId(event.getGroupId());
        ret.setId((Long) map.get("id"));
        ret.setIsEditable(Boolean.TRUE);
        ret.setStartDate(event.getStartDate());
        ret.setScheduleId(event.getId());
        ret.setStyleClass(event.getStyleClass());
        ret.setTitle(event.getTitle());
        ret.setUrl(event.getUrl());
        ret.setUserName(getCurrentUser());
        return ret;
    }


    private String getDescription(DefaultScheduleEvent event) {

        String ret;
        if (isBlank(event.getDescription())) {
            ret = event.getTitle();
        } else {
            ret = event.getDescription();
        }
        return ret;
    }


    private UserEntity getCurrentUser() {

        Long userId = (Long) map.get("id");
        return userService.getUser(userId)
                .get();
    }

}
