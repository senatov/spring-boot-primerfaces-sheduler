package de.senatov.reservatio.db;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Slf4j
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SC_SCHEDULE", schema = "SCHEDULE_DB")
public class ScheduleEntity implements Serializable {

	private static final long serialVersionUID = 4411986672561000356L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "username_fk"))
	private UserEntity userName;
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
	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;
	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;

}
