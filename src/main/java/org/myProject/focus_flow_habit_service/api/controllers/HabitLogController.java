package org.myProject.focus_flow_habit_service.api.controllers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


import io.swagger.v3.oas.annotations.Operation;
import org.myProject.focus_flow_habit_service.api.controllers.helpers.HabitHelper;
import org.myProject.focus_flow_habit_service.api.controllers.helpers.HabitLogHelper;
import org.myProject.focus_flow_habit_service.api.dto.AnsDto;
import org.myProject.focus_flow_habit_service.api.dto.HabitLogDto;
import org.myProject.focus_flow_habit_service.api.factories.HabitLogDtoFactory;
import org.myProject.focus_flow_habit_service.store.entities.HabitEntity;
import org.myProject.focus_flow_habit_service.store.entities.HabitLogEntity;
import org.myProject.focus_flow_habit_service.store.repositories.HabitLogRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HabitLogController {

    HabitLogRepository habitLogRepository;

    HabitLogDtoFactory habitLogDtoFactory;

    HabitHelper habitHelper;

    HabitLogHelper habitLogHelper;

    private final static String GET_HABIT_LOGS = "/api/habit-logs/{habit_id}";
    private final static String CREATE_HABIT_LOG = "/api/habit-logs/{habit_id}";
    private final static String UPDATE_HABIT_LOG = "/api/habit-logs/{habit_log_id}";
    private final static String DELETE_HABIT_LOG = "/api/habit-logs/{habit_log_id}";

    @Operation(summary = "Retrieve habit logs", description = "Fetches the log entries of a specific habit based on habit ID and user ID.")
    @GetMapping(GET_HABIT_LOGS)
    public List<HabitLogDto> getHabitLogs(
            @PathVariable("habit_id") Long habitId,
            @AuthenticationPrincipal Jwt jwt) {

        Long userId = Long.parseLong(jwt.getSubject());

        habitHelper.getHabitOrThrowException(habitId, userId);

        List<HabitLogEntity> habitLogs = habitLogRepository.findByHabitId(habitId);

        return habitLogs
                .stream()
                .map(habitLogDtoFactory::makeHabitLogDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Create a habit log", description = "Creates a new log entry for a habit with a scheduled date and completion status.")
    @PostMapping(CREATE_HABIT_LOG)
    public HabitLogDto createHabitLog(
            @PathVariable("habit_id") Long habitId,
            @RequestParam("scheduled_date") LocalDateTime scheduledDate,
            @RequestParam("is_completed") boolean isCompleted,
            @AuthenticationPrincipal Jwt jwt){

        Long userId = Long.parseLong(jwt.getSubject());

        HabitEntity habit = habitHelper.getHabitOrThrowException(habitId, userId);

        HabitLogEntity habitLog = habitLogRepository.save(

                HabitLogEntity
                        .builder()
                        .habitId(habit.getId())
                        .scheduledDate(scheduledDate)
                        .isCompleted(isCompleted)
                        .build());

        return habitLogDtoFactory.makeHabitLogDto(habitLog);
    }

    @Operation(summary = "Update a habit log", description = "Updates an existing habit log entry with a new scheduled date and completion status.")
    @PatchMapping(UPDATE_HABIT_LOG)
    public HabitLogDto updateHabitLog(
            @PathVariable("habit_log_id") Long habitLogId,
            @RequestParam("scheduled_date") LocalDateTime scheduledDate,
            @RequestParam("is_completed") boolean isCompleted,
            @AuthenticationPrincipal Jwt jwt){

        Long userId = Long.parseLong(jwt.getSubject());

        HabitLogEntity habitLog = habitLogHelper.getHabitLogEntityOrThrowException(habitLogId, userId);

        habitLog.setScheduledDate(scheduledDate);
        habitLog.setCompleted(isCompleted);

        habitLogRepository.save(habitLog);

        return habitLogDtoFactory.makeHabitLogDto(habitLog);
    }

    @Operation(summary = "Delete a habit log", description = "Deletes a habit log entry if it belongs to the given user.")
    @DeleteMapping(DELETE_HABIT_LOG)
    public AnsDto deleteHabitLog(
            @PathVariable("habit_log_id") Long habitLogId,
            @AuthenticationPrincipal Jwt jwt){

        Long userId = Long.parseLong(jwt.getSubject());

        HabitLogEntity habitLog = habitLogHelper.getHabitLogEntityOrThrowException(habitLogId, userId);

        habitLogRepository.delete(habitLog);

        return AnsDto.builder().answer(true).build();
    }

}
