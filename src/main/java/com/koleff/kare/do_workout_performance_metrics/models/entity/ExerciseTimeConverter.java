package com.koleff.kare.do_workout_performance_metrics.models.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExerciseTimeConverter implements AttributeConverter<ExerciseTime, String> {

    @Override
    public String convertToDatabaseColumn(ExerciseTime attribute) {
        if (attribute == null) return null;
        return attribute.toString();
    }

    @Override
    public ExerciseTime convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return null;
        String[] parts = dbData.split(":");
        return new ExerciseTime(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        );
    }
}