package com.koleff.kare.exercise.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MuscleGroup {
    CHEST(1, "Chest", ""),
    BACK(2, "Back", ""),
    TRICEPS(3, "Triceps", ""),
    BICEPS(4, "Biceps", ""),
    SHOULDERS(5, "Shoulders", ""),
    LEGS(6, "Legs", ""),
    ABS(7, "Abs", ""),
    CARDIO(8, "Cardio", ""),
    FULL_BODY(9, "Full Body", ""),
    PUSH_PULL_LEGS(10, "Push Pull Legs", ""),
    UPPER_LOWER_BODY(11, "Upper Lower Body", ""),
    ARMS(12, "Arms", ""),
    OTHER(13, "Other", ""),
    ALL(14, "All", ""),
    NONE(-1, "None", "");

    private final int muscleGroupId;
    private final String muscleGroupName;
    private final String description;

    public static MuscleGroup fromId(int id) {
        for (MuscleGroup muscleGroup : values()) {
            if (muscleGroup.getMuscleGroupId() == id) {
                return muscleGroup;
            }
        }
        return NONE;
    }
}
