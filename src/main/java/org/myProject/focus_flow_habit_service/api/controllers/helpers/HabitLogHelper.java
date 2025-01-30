package org.myProject.focus_flow_habit_service.api.controllers.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_habit_service.api.exceptions.CustomAppException;
import org.myProject.focus_flow_habit_service.store.entities.HabitLogEntity;
import org.myProject.focus_flow_habit_service.store.repositories.HabitLogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Transactional
public class HabitLogHelper {

    HabitLogRepository habitLogRepository;

    public HabitLogEntity getHabitLogEntityOrThrowException(Long habitLogId) {

        return habitLogRepository
                .findById(habitLogId)
                .orElseThrow(() ->
                        new CustomAppException(
                                HttpStatus.BAD_REQUEST,
                                String.format("Habit log with id %s doesn't exist", habitLogId)));
    }
}
