package com.koleff.kare.models.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Permission {

    CREATE_WORKOUT(1, "user:create_workout"),
    DELETE_WORKOUT(2, "user:delete_workout"),
    FETCH_WORKOUTS(3, "user:fetch_workouts"),
    SELECT_WORKOUT(4, "user:select_workout"),
    RENAME_WORKOUT(5, "user:rename_workout"),
    ADD_EXERCISE(6, "user:add_exercise"),
    DELETE_EXERCISE(7, "user:delete_exercise");

    private final Integer id;
    private final String permission;
}
