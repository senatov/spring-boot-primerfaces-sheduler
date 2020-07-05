package de.senatov.reservatio.db;



import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Slf4j
@ToString
@Data
@Entity
@Table(name = "SC_SCHEDULE", schema = "SCHEDULE_DB")
public class ScheduleEntity implements Serializable {

	private static final long serialVersionUID = 4411986672561000356L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "start_date", updatable = true, nullable = false)
	private LocalDateTime startDate;
	@Column(name = "end_date", updatable = true, nullable = false)
	private LocalDateTime endDate;
	@Column(name = "title")
	String title;
	@Column(name = "description")
	String description;
	@Column(name = "group_id")
	String groupId;
	@Column(name = "schedule_id")
	String scheduleId;
	@Column(name = "is_editable")
	@ColumnDefault("TRUE")
	Boolean isEditable;
	@Column(name = "style_class")
	String styleClass;
	@Column(name = "url")
	String url;

}
