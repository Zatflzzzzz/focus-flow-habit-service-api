package org.myProject.focus_flow_habit_service.api.controllers.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_habit_service.api.exceptions.CustomAppException;
import org.myProject.focus_flow_habit_service.store.entities.HabitEntity;
import org.myProject.focus_flow_habit_service.store.entities.HabitLogEntity;
import org.myProject.focus_flow_habit_service.store.repositories.HabitLogRepository;
import org.myProject.focus_flow_habit_service.store.repositories.HabitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Transactional
public class HabitLogHelper {

    HabitLogRepository habitLogRepository;

    HabitHelper habitHelper;

    ValidateRequestsHelper validateRequestsHelper;

    public HabitLogEntity getHabitLogEntityOrThrowException(Long habitLogId, Long userId) {

        HabitLogEntity habitLog = habitLogRepository
                .findById(habitLogId)
                .orElseThrow(() ->
                        new CustomAppException(
                                HttpStatus.BAD_REQUEST,
                                String.format("Habit log with id %s doesn't exist", habitLogId)));

        validateRequestsHelper.verifyingUserAccessToHabit(habitLog.getHabitId(), userId);

        return habitLog;
    }
}
