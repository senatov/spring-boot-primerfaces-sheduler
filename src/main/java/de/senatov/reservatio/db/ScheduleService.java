package de.senatov.reservatio.db;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
@ToString
public class ScheduleService implements Serializable {

	@Serial
	private static final long serialVersionUID = 3113025681276026951L;
	private final ScheduleRepository scheduleRepository;


	@Autowired
	public ScheduleService(ScheduleRepository scheduleRepository) {

		this.scheduleRepository = scheduleRepository;
	}


	public List<ScheduleEntity> getAllSchedules() {

		log.debug("getAllSchedules()");
		List<ScheduleEntity> scheduleEntities = new ArrayList<>(4);
		scheduleEntities.addAll(scheduleRepository.findAll());
		return scheduleEntities;
	}


	public Optional<ScheduleEntity> getSchedule(Long id) {

		log.debug("getScheduleEntity()");
		return scheduleRepository.findById(id);
	}


	public void addSchedule(ScheduleEntity entity) {

		log.debug("addSchedule()");
		scheduleRepository.save(entity);
	}


	public void updateSchedule(ScheduleEntity entity) {

		log.debug("updateSchedule()");
		scheduleRepository.save(entity);
	}


	public void deleteSchedule(ScheduleEntity entity) {

		log.debug("deleteSchedule()");
		scheduleRepository.delete(entity);
	}

}
