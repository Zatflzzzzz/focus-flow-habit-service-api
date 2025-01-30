package org.myProject.focus_flow_habit_service.api.controllers.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_habit_service.api.exceptions.CustomAppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Transactional
public class ValidateRequestsHelper {

    public void verifyingUserAccessToHabit(Long habitOwnerId, Long currentUserId) {

        if (!Objects.equals(habitOwnerId, currentUserId)) {
            throw new CustomAppException(
                    HttpStatus.FORBIDDEN, "You does not have access to this habit"
            );
        }
    }
}
