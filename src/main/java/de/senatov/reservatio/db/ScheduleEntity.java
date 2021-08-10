package de.senatov.reservatio.db;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;



@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sc_schedule")
@Entity(name = "sc_schedule")

public class ScheduleEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 4411986672561000356L;
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "description")
	String description;
	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;
	@Column(name = "group_id")
	String groupId;
	@Column(name = "is_editable")
	@ColumnDefault("TRUE")
	Boolean isEditable;
	@Column(name = "schedule_id")
	String scheduleId;
	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;
	@Column(name = "style_class")
	String styleClass;
	@Column(name = "title")
	String title;
	@Column(name = "url")
	String url;
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "username_fk"))
	private UserEntity userEntity;

}
