package de.senatov.reservatio.utl;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;



@Configuration
@Data
@Slf4j
public class ScheduleRecordMapper {

	private static String SELECT_ALL_FROM_VIEW = "select * from schedule_db.schedule_user ORDER BY start_date, end_date";
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



	public void extractVal(Object sheduleMaps) {

		map = (LinkedCaseInsensitiveMap) sheduleMaps;
		title = String.valueOf(map.get("title"));
		startDate = ((Timestamp) map.get("start_date")).toLocalDateTime();
		endDate = ((Timestamp) map.get("end_date")).toLocalDateTime();
		description = String.valueOf(map.get("description"));
		groupId = String.valueOf(map.get("group_id"));
		id = String.valueOf(map.get("id"));
		isEditable = (Boolean) map.get("is_editable");
		style = String.valueOf(map.get("style_class"));
		url = String.valueOf(map.get("url"));
	}

}
