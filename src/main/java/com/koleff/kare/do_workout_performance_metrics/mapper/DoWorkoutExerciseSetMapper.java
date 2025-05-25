package com.koleff.kare.do_workout_performance_metrics.mapper;

import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutExerciseSetDto;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutExerciseSet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoWorkoutExerciseSetMapper {

    @Mapping(source = "doWorkoutPerformanceMetricsId", target = "doWorkoutPerformanceMetricsId")
    DoWorkoutExerciseSetDto toDto(DoWorkoutExerciseSet entity);

    DoWorkoutExerciseSet toEntity(DoWorkoutExerciseSetDto dto);
}