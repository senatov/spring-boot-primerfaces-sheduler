package de.senatov.reservatio.db;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
public class ScheduleService implements Serializable {

	@Serial
	private static final long serialVersionUID = 3113025681276026951L;
	private final ScheduleRepository scheduleRepository;

	public ScheduleService(ScheduleRepository scheduleRepository) {

		this.scheduleRepository = scheduleRepository;
	}


	public List<ScheduleEntity> getAllSchedules() {

		log.debug("getAllSchedules()");
		List<ScheduleEntity> scheduleEntities = new ArrayList<>(0xF);
		scheduleEntities.addAll(scheduleRepository.findAll());
		return scheduleEntities;
	}


	public Optional<ScheduleEntity> getSchedule(Long id) {

		log.debug("getScheduleEntity({})", id);
		return scheduleRepository.findById(id);
	}


	public void addSchedule(ScheduleEntity scheduleEntity) {

		log.debug("addSchedule()");
		scheduleRepository.save(scheduleEntity);
	}


	public void updateSchedule(ScheduleEntity scheduleEntity) {
		log.debug("updateSchedule()");
		scheduleRepository.delete(scheduleEntity);
		scheduleEntity = scheduleRepository.saveAndFlush(scheduleEntity);
		log.debug("updated Entity = {}", scheduleEntity);
	}


	public void deleteSchedule(ScheduleEntity scheduleEntity) {

		log.debug("deleteSchedule()");
		scheduleRepository.delete(scheduleEntity);
	}

}
