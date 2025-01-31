package org.myProject.focus_flow_habit_service.api.factories;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_habit_service.api.dto.HabitDto;
import org.myProject.focus_flow_habit_service.store.entities.HabitEntity;
import org.myProject.focus_flow_habit_service.store.repositories.HabitLogRepository;
import org.myProject.focus_flow_habit_service.store.repositories.HabitRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HabitDtoFactory {

    HabitLogDtoFactory habitLogDtoFactory;

    private final HabitLogRepository habitLogRepository;

    public HabitDto makeHabitDto(HabitEntity habit) {

        return HabitDto
                .builder()
                .id(habit.getId())
                .title(habit.getTitle())
                .description(habit.getDescription())
                .dueDate(habit.getDueDate())
                .timeToComplete(habit.getTimeToComplete())
                .userId(habit.getUserId())
                .createdAt(habit.getCreatedAt())
                .lastAction(habit.getLastAction())
                .habitLogs(
                        habitLogRepository
                        .findByHabitId(habit.getId())
                        .stream()
                        .map(habitLogDtoFactory::makeHabitLogDto)
                        .collect(Collectors.toList())
                )
                .build();
    }
}
