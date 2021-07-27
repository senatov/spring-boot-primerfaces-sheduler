package de.senatov.reservatio.db;


import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@ToString
public class ScheduleService implements Serializable {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {

        this.scheduleRepository = scheduleRepository;
    }


    public List<ScheduleEntity> getAllSchedules() {

        log.debug("getAllSchedules()");
        List<ScheduleEntity> ScheduleEntities = new ArrayList<>(0xF);
        scheduleRepository.findAll().forEach(ScheduleEntities::add);
        return ScheduleEntities;
    }


    public Optional<ScheduleEntity> getSchedule(Long id) {

        log.debug("getScheduleEntity({})", id);
        return scheduleRepository.findById(id);
    }


    public void addSchedule(ScheduleEntity ScheduleEntity) {

        log.debug("addSchedule()");
        scheduleRepository.save(ScheduleEntity);
    }


    public void updateSchedule(ScheduleEntity ScheduleEntity) {

        log.debug("updateSchedule()");
        scheduleRepository.save(ScheduleEntity);
    }


    public void deleteSchedule(ScheduleEntity ScheduleEntity) {

        log.debug("deleteSchedule()");
        scheduleRepository.delete(ScheduleEntity);
    }

}
