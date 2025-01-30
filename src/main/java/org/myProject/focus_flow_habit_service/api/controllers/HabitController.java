package org.myProject.focus_flow_habit_service.api.controllers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_habit_service.api.controllers.helpers.HabitHelper;
import org.myProject.focus_flow_habit_service.api.dto.AnsDto;
import org.myProject.focus_flow_habit_service.api.dto.HabitDto;
import org.myProject.focus_flow_habit_service.api.exceptions.CustomAppException;
import org.myProject.focus_flow_habit_service.api.factories.HabitDtoFactory;
import org.myProject.focus_flow_habit_service.store.entities.HabitEntity;
import org.myProject.focus_flow_habit_service.store.repositories.HabitRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HabitController {

    HabitRepository habitRepository;

    HabitDtoFactory habitDtoFactory;

    HabitHelper habitHelper;

    private final static String GET_HABITS = "/api/habits";
    private final static String GET_HABIT = "/api/habits/{habit_id}";
    private final static String CREATE_HABIT = "/api/habits";
    private final static String UPDATE_HABIT = "/api/habits/{habit_id}";
    private final static String DELETE_HABIT = "/api/habits/{habit_id}";

    @GetMapping(GET_HABIT)
    public HabitDto getHabit(
            @PathVariable("habit_id") Long habitId,
            @RequestParam("user_id") Long userId){

        HabitEntity habit = habitHelper.getHabitOrThrowException(habitId, userId);

        return habitDtoFactory.makeHabitDto(habit);
    }

    @GetMapping(GET_HABITS)
    public List<HabitDto> getHabits(@RequestParam("user_id") Long userId){

        Stream<HabitEntity> habits = habitRepository
                .streamAllByUserId(userId);

        return habits
                .map(habitDtoFactory::makeHabitDto)
                .collect(Collectors.toList());
    }

    @PostMapping(CREATE_HABIT)
    public HabitDto createHabit(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam("time_to_complete") LocalTime timeToComplete,
            @RequestParam("due_date") LocalDateTime dueDate,
            @RequestParam("user_id") Long userId){

        description = habitHelper.checkForValidityOfData(title, description, userId);

        HabitEntity habit = habitRepository.save(
                HabitEntity
                        .builder()
                        .title(title)
                        .description(description)
                        .timeToComplete(timeToComplete)
                        .dueDate(dueDate)
                        .userId(userId)
                        .build()
        );

        return habitDtoFactory.makeHabitDto(habit);
    }

    @PatchMapping(UPDATE_HABIT)
    public HabitDto updateHabit(
            @PathVariable("habit_id") Long habitId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam("time_to_complete") LocalTime timeToComplete,
            @RequestParam("due_date") LocalDateTime dueDate,
            @RequestParam("user_id") Long userId){

        description = habitHelper.checkForValidityOfData(title, description, userId);

        HabitEntity habit = habitHelper.getHabitOrThrowException(habitId, userId);

        habit.setTitle(title);
        habit.setDescription(description);
        habit.setTimeToComplete(timeToComplete);
        habit.setDueDate(dueDate);

        habitRepository.save(habit);

        return habitDtoFactory.makeHabitDto(habit);
    }

    @DeleteMapping(DELETE_HABIT)
    public AnsDto deleteHabit(
            @PathVariable("habit_id") Long habitId,
            @RequestParam("user_id") Long userId){

        HabitEntity habit = habitHelper.getHabitOrThrowException(habitId, userId);

        habitRepository.delete(habit);

        return AnsDto.builder().answer(true).build();
    }
}
