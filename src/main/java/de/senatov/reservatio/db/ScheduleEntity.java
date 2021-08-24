package de.senatov.reservatio.db;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "group_id")
    private String groupId;
    @Column(name = "is_editable")
    @ColumnDefault("TRUE")
    private Boolean isEditable;
    @Column(name = "schedule_id")
    private String scheduleId;
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    @Column(name = "style_class")
    private String styleClass;
    @Column(name = "title")
    private String title;
    @Column(name = "url")
    private String url;
    @CreatedBy
    @ColumnDefault("0")
    private String creator;
    @LastModifiedBy
    @ColumnDefault("0")
    private String modifier;
    @CreatedDate
    private LocalDateTime  createdAt;
    @LastModifiedDate
    @ColumnDefault("now()")
    private LocalDateTime  modifiedAt;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    @JoinColumn(name = "userentity_id", foreignKey = @ForeignKey(name = "username_fk"))
    private UserEntity userEntity;

}
