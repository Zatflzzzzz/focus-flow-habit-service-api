package org.myProject.focus_flow_habit_service.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HabitLogDto {

    @NonNull
    Long id;

    @NonNull
    LocalDateTime scheduledDate;

    boolean isCompleted;
}
