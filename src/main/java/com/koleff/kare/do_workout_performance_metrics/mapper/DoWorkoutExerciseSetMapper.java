package com.koleff.kare.do_workout_performance_metrics.mapper;

import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutExerciseSetDto;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutExerciseSet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoWorkoutExerciseSetMapper {

    DoWorkoutExerciseSetDto toDto(DoWorkoutExerciseSet entity);

    DoWorkoutExerciseSet toEntity(DoWorkoutExerciseSetDto dto);
}