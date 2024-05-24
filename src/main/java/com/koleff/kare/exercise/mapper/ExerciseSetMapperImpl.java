//package com.koleff.kare.exercise.mapper;
//
//import com.koleff.kare.exercise.models.dto.ExerciseDto;
//import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
//import com.koleff.kare.exercise.models.entity.Exercise;
//import com.koleff.kare.exercise.models.entity.ExerciseSet;
//
//public class ExerciseSetMapperImpl implements ExerciseSetMapper {
//    @Override
//    public ExerciseSetDto toDto(ExerciseSet exerciseSet) {
//        return new ExerciseSetDto(
//                exerciseSet.getExerciseSetId(),
//                exerciseSet.getNumber(),
//                exerciseSet.getExerciseId(),
//                exerciseSet.getWorkoutId(),
//                exerciseSet.getReps(),
//                exerciseSet.getWeight()
//        );
//    }
//
//    @Override
//    public ExerciseSet toEntity(ExerciseSetDto exerciseSetDto) {
//        return new ExerciseSet(
//                exerciseSetDto.id(),
//                exerciseSetDto.number(),
//                exerciseSetDto.exerciseId(),
//                exerciseSetDto.workoutId(),
//                exerciseSetDto.reps(),
//                exerciseSetDto.weight()
//        );
//    }
//}
