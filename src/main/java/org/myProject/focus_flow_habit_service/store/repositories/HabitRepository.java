package org.myProject.focus_flow_habit_service.store.repositories;

import org.myProject.focus_flow_habit_service.store.entities.HabitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface HabitRepository extends CrudRepository<HabitEntity, Long> {

    List<HabitEntity> findAllByUserId(long userId);
}
