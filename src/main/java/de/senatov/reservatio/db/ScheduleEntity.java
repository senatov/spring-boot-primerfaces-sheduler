package de.senatov.reservatio.db;



import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@Entity
@Table(catalog = "schedule_db", schema = "public", name = "SC_SCHEDULE")
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
