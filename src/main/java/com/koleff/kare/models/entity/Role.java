package com.koleff.kare.models.entity;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public enum Role {

    USER(1,
            Set.of(
                    Permission.CREATE_WORKOUT,
                    Permission.DELETE_WORKOUT,
                    Permission.FETCH_WORKOUTS,
                    Permission.SELECT_WORKOUT,
                    Permission.RENAME_WORKOUT,
                    Permission.ADD_EXERCISE,
                    Permission.DELETE_EXERCISE
            )
    ),

    ADMIN(2,
            Set.of(
                    Permission.CREATE_WORKOUT,
                    Permission.DELETE_WORKOUT,
                    Permission.FETCH_WORKOUTS,
                    Permission.SELECT_WORKOUT,
                    Permission.RENAME_WORKOUT,
                    Permission.ADD_EXERCISE,
                    Permission.DELETE_EXERCISE
            )
    ),

    COACH(3,
          Set.of(
                  Permission.CREATE_WORKOUT,
          Permission.DELETE_WORKOUT,
          Permission.FETCH_WORKOUTS,
          Permission.SELECT_WORKOUT,
          Permission.RENAME_WORKOUT,
          Permission.ADD_EXERCISE,
          Permission.DELETE_EXERCISE
          )
    );


    private final Integer roleId;
    private final Set<Permission> permissions;
}

