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
	public ScheduleService(ScheduleRepository scheduleRepository) throws Exception{

		this.scheduleRepository = scheduleRepository;
	}


	public List<ScheduleEntity> getAllSchedules() throws Exception{

		log.debug("getAllSchedules()");
		List<ScheduleEntity> scheduleEntities = new ArrayList<>(4);
		scheduleEntities.addAll(scheduleRepository.findAll());
		return scheduleEntities;
	}


	public Optional<ScheduleEntity> getSchedule(Long id) throws Exception{

		log.debug("getScheduleEntity()");
		return scheduleRepository.findById(id);
	}


	public void addSchedule(ScheduleEntity entity) throws Exception{

		log.debug("addSchedule()");
		scheduleRepository.save(entity);
	}


	public void saveSchedule(ScheduleEntity entity) throws Exception{

		log.debug("saveSchedule()");
		scheduleRepository.save(entity);
	}


	public void deleteSchedule(ScheduleEntity entity) throws Exception{

		log.debug("deleteSchedule()");
		scheduleRepository.delete(entity);
	}

}
