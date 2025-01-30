package org.myProject.focus_flow_habit_service.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnsDto {

    Boolean answer;

    public static AnsDto makeDefault(Boolean answer) {
        return builder()
                .answer(answer)
                .build();
    }
}
