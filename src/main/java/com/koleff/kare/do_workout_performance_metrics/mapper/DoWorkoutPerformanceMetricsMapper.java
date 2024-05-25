package com.koleff.kare.do_workout_performance_metrics.mapper;

import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutPerformanceMetricsDto;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutPerformanceMetrics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DoWorkoutExerciseSetMapper.class})
public interface DoWorkoutPerformanceMetricsMapper {

    @Mapping(source = "doWorkoutExerciseSets", target = "doWorkoutExerciseSets")
    DoWorkoutPerformanceMetricsDto toDto(DoWorkoutPerformanceMetrics entity);

    DoWorkoutPerformanceMetrics toEntity(DoWorkoutPerformanceMetricsDto dto);
}