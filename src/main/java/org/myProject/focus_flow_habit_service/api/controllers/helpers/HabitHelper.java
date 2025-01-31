package org.myProject.focus_flow_habit_service.api.controllers.helpers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_habit_service.api.exceptions.CustomAppException;
import org.myProject.focus_flow_habit_service.store.entities.HabitEntity;
import org.myProject.focus_flow_habit_service.store.repositories.HabitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Transactional
public class HabitHelper {

    HabitRepository habitRepository;

    ValidateRequestsHelper validateRequestsHelper;

    public HabitEntity getHabitOrThrowException(Long habitId, Long userId) {

        validateRequestsHelper.verifyingUserAccessToHabit(habitId, userId);

        return habitRepository
                .findById(habitId)
                .orElseThrow(() ->
                        new CustomAppException(
                                HttpStatus.BAD_REQUEST,
                                String.format("Habit with id %s doesn't exist", habitId)));
    }

    public String checkForValidityOfData(String title, String description, Long userId) {

        if(title.trim().isEmpty()){
            throw new CustomAppException(HttpStatus.BAD_REQUEST, "title cannot be empty");
        }

        if(description.trim().isEmpty()) description = "";

        habitRepository
                .findAllByUserId(userId)
                .stream()
                .filter(anotherHabit -> anotherHabit.getTitle().equals(title))
                .findAny()
                .ifPresent(anotherHabit -> {

                    throw new CustomAppException(HttpStatus.BAD_REQUEST,
                            String.format("Habit %s already exists", anotherHabit.getTitle()));
                });

        return description;
    }
}
