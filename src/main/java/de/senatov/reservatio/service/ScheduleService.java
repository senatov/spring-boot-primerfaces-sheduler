package de.senatov.reservatio.service;


import de.senatov.reservatio.db.ScheduleEntity;
import de.senatov.reservatio.db.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.DefaultScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ScheduleService implements Serializable {

    @Serial
    private static final long serialVersionUID = 3113025681276026951L;
    private final ScheduleRepository scheduleRepository;


    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) throws Exception {

        this.scheduleRepository = scheduleRepository;
    }


    public List<ScheduleEntity> getAllSchedules() throws Exception {

        log.debug("getAllSchedules()");
        List<ScheduleEntity> scheduleEntities = new ArrayList<>(4);
        scheduleEntities.addAll(scheduleRepository.findAll());
        return scheduleEntities;
    }


    public Long findMaxSheduleId() throws Exception {
        return scheduleRepository.findMaxSheduleId();

    }


    public Optional<ScheduleEntity> getSchedule(Long id) throws Exception {

        log.debug("getScheduleEntity()");
        return scheduleRepository.findById(id);
    }


    public void addSchedule(ScheduleEntity entity) throws Exception {

        log.debug("addSchedule()");
        scheduleRepository.save(entity);
    }


    public void updateSchedule(DefaultScheduleEvent newEntity) throws Exception {

        log.debug("updateSchedule()");
        ScheduleEntity entity = scheduleRepository.findBySheduleId(newEntity.getId());
        scheduleRepository.deleteById(entity.getId());
        entity.setDescription(newEntity.getDescription());
        entity.setModifiedAt(LocalDateTime.now());
        entity.setModifier(System.getProperty("user"));
        entity.setGroupId(newEntity.getGroupId());
        entity.setStartDate(newEntity.getStartDate());
        entity.setEndDate(newEntity.getEndDate());
        scheduleRepository.save(entity);
    }


    public void saveSchedule(ScheduleEntity entity) throws Exception {

        log.debug("saveSchedule()");
        scheduleRepository.save(entity);
    }


    public void deleteSchedule(ScheduleEntity entity) throws Exception {

        log.debug("deleteSchedule()");
        try {
            ScheduleEntity fullEntity = scheduleRepository.findBySheduleId("" + entity.getId());
            scheduleRepository.deleteById(fullEntity.getId());

        } catch (EmptyResultDataAccessException erdae) {
            log.warn("schedule with id={} not exists", entity.getId());
        }
    }

}
