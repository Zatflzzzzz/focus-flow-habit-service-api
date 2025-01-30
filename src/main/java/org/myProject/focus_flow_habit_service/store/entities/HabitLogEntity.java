package org.myProject.focus_flow_habit_service.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("habit_entries")
public class HabitLogEntity {

    LocalDateTime scheduledDate;

    boolean isCompleted;

    Long habitId;
}
