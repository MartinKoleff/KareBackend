//package com.koleff.kare.exercise.mapper;
//
//import com.koleff.kare.exercise.models.dto.ExerciseDto;
//import com.koleff.kare.exercise.models.entity.Exercise;
//
//public class ExerciseMapperImpl implements ExerciseMapper {
//
//    private final ExerciseSetMapper exerciseSetMapper;
//
//    @Override
//    public ExerciseDto toDto(Exercise exercise) {
//        return new ExerciseDto(
//                exercise.getExerciseId(),
//                exercise.getName(),
//                exercise.getMuscleGroupId(),
//                exercise.getMachineTypeId(),
//                exercise.getSnapshot(),
//                exercise.getSets()
//        );
//    }
//
//    @Override
//    public Exercise toEntity(ExerciseDto exerciseDto) {
//        return null;
//    }
//}
