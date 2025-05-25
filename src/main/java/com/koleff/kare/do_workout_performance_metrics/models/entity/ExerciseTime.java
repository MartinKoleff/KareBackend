package com.koleff.kare.do_workout_performance_metrics.models.entity;

import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Convert(converter = ExerciseTimeConverter.class)
public @Data class ExerciseTime {
    private int hours;
    private int minutes;
    private int seconds;

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}