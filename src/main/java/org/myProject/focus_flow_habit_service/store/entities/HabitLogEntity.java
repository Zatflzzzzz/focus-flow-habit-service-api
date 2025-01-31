package org.myProject.focus_flow_habit_service.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("habit_logs")
public class HabitLogEntity {

    @Id
    Long id;

    LocalDateTime scheduledDate;

    boolean isCompleted;

    @Column("habit_id")
    Long habitId;
}
