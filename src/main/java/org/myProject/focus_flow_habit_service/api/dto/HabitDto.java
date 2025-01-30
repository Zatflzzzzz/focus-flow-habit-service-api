package org.myProject.focus_flow_habit_service.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class HabitDto {

    @NonNull
    Long id;

    @NonNull
    @JsonProperty("time_to_complete")
    LocalTime timeToComplete;

    @NonNull
    @JsonProperty("due_date")
    LocalDateTime dueDate;

    @NonNull
    String title;

    @NonNull
    String description;

    @NonNull
    @JsonProperty("user_id")
    Long userId;

    @NonNull
    @JsonProperty("last_action")
    LocalDateTime lastAction;

    @NonNull
    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @NonNull
    List<HabitLogDto> habitLogs;
}
