package org.myProject.focus_flow_habit_service.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("habit")
public class HabitEntity{

    @Id
    Long id;

    String title;

    String description;

    LocalTime timeToComplete;

    LocalDateTime dueDate;

    Long userId;

    @Builder.Default
    LocalDateTime lastAction = LocalDateTime.now();

    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();

    @MappedCollection(idColumn = "habit_id")
    @Builder.Default
    List<HabitLogEntity> habitLogs = new ArrayList<>();
}
