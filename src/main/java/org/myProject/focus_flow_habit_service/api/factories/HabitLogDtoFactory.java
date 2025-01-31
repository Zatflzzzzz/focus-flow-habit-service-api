package org.myProject.focus_flow_habit_service.api.factories;

import org.myProject.focus_flow_habit_service.api.dto.HabitLogDto;
import org.myProject.focus_flow_habit_service.store.entities.HabitLogEntity;
import org.springframework.stereotype.Component;

@Component
public class HabitLogDtoFactory {

    public HabitLogDto makeHabitLogDto(HabitLogEntity habitLog) {

        return HabitLogDto
                .builder()
                .id(habitLog.getId())
                .isCompleted(habitLog.isCompleted())
                .scheduledDate(habitLog.getScheduledDate())
                .build();
    }
}
