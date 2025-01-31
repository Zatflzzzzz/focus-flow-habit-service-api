package org.myProject.focus_flow_habit_service.store.repositories;

import org.myProject.focus_flow_habit_service.store.entities.HabitLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitLogRepository extends CrudRepository<HabitLogEntity, Long> {

    List<HabitLogEntity> findByHabitId(Long habitId);
}
