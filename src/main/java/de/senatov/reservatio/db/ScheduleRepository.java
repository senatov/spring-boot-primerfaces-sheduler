package de.senatov.reservatio.db;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query("select s from sc_schedule s where s.scheduleId=?1")
    ScheduleEntity findBySheduleId(String scheduleId);

    @Query("select max(s.id) from sc_schedule s")
    Long findMaxSheduleId();

}
