package com.koleff.kare.do_workout_performance_metrics.models.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public @Data class ExerciseTime {
    private int hours;
    private int minutes;
    private int seconds;
}