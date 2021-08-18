package de.senatov.reservatio.db;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

		//@Query("update sc_schedule s set s.description = :description, s.title=:title, s.endDate= :endDate, s.startDate=:startDate   WHERE s.id = :id")
		//void update(@Param("description") Long description, @Param("name") String name);
}
