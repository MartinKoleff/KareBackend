package com.koleff.kare.workout.models.service;

import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.mapper.ExerciseSetMapper;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.exercise.repository.ExerciseRepository;
import com.koleff.kare.exercise.repository.ExerciseSetRepository;
import com.koleff.kare.exercise.service.ExerciseService;
import com.koleff.kare.workout.mapper.WorkoutDetailsMapper;
import com.koleff.kare.workout.mapper.WorkoutMapper;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import com.koleff.kare.workout.repository.WorkoutDetailsRepository;
import com.koleff.kare.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WorkoutCustomServiceImpl implements WorkoutCustomService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;
    private final WorkoutDetailsRepository workoutDetailsRepository;
    private final WorkoutDetailsMapper workoutDetailsMapper;
    private final ExerciseMapper exerciseMapper;
    private final ExerciseSetMapper exerciseSetMapper;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseSetRepository exerciseSetRepository;

    @Autowired
    public WorkoutCustomServiceImpl(
            WorkoutRepository workoutRepository,
            WorkoutMapper workoutMapper,
            WorkoutDetailsRepository workoutDetailsRepository,
            WorkoutDetailsMapper workoutDetailsMapper,
            ExerciseMapper exerciseMapper,
            ExerciseSetMapper exerciseSetMapper,
            ExerciseRepository exerciseRepository,
            ExerciseSetRepository exerciseSetRepository
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.workoutDetailsRepository = workoutDetailsRepository;
        this.workoutDetailsMapper = workoutDetailsMapper;
        this.exerciseMapper = exerciseMapper;
        this.exerciseSetMapper = exerciseSetMapper;
        this.exerciseRepository = exerciseRepository;
        this.exerciseSetRepository = exerciseSetRepository;
    }

    @Override
    public WorkoutDto createNewWorkout() {
        return null;
    }

    @Override
    public WorkoutDto createCustomWorkout(WorkoutDto workout) {
        return null;
    }

    @Override
    public WorkoutDetailsDto createCustomWorkoutDetails(WorkoutDetailsDto workoutDetailsDto) {
        return null;
    }

    @Override
    public WorkoutDetailsDto addExercise(Long workoutId, ExerciseDto exercise) {

        //Validation
        if (exercise.workoutId() == -1 || !exercise.workoutId().equals(workoutId)) {
            //TODO: throw KareError.INVALID_EXERCISE
        }

        insertExerciseAndSets(exercise, exercise.sets());

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            //TODO: throw KareError.WORKOUT_DETAILS_NOT_FOUND
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto addMultipleExercises(Long workoutId, List<ExerciseDto> exercises) {

        //Validation
        for (ExerciseDto exercise : exercises) {
            if (!exercise.workoutId().equals(workoutId)) {
                //TODO: throw KareError.INVALID_EXERCISE
            }
        }

        for (ExerciseDto exercise : exercises) {
            insertExerciseAndSets(exercise, exercise.sets());
        }

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            //TODO: throw KareError.WORKOUT_DETAILS_NOT_FOUND
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto submitExercise(Long workoutId, ExerciseDto exercise) {
        //Validation
        if (exercise.workoutId() == -1 || !exercise.workoutId().equals(workoutId)) {
            //TODO: throw KareError.INVALID_EXERCISE
        }

        try {

            //Entry in DB exists -> update
            Exercise dbEntry = exerciseRepository.findByExerciseIdAndWorkoutId(exercise.id(), exercise.workoutId())
                    .orElseThrow(() -> new NoSuchElementException( //TODO: throw KareError.EXERCISE_NOT_FOUND
                            String.format("No exercise found with exerciseId %d and workoutId %d", exercise.id(), exercise.workoutId())
                    ));

            updateExercise(exerciseMapper.toDto(dbEntry), exercise);
        }catch (NoSuchElementException e){

            //Exercise not found -> new entry...
            insertExerciseAndSets(exercise, exercise.sets());
        }

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            //TODO: throw KareError.WORKOUT_DETAILS_NOT_FOUND
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto submitMultipleExercises(Long workoutId, List<ExerciseDto> exercises) {
        //Validation
        for (ExerciseDto exercise : exercises) {
            if (!exercise.workoutId().equals(workoutId)) {
                //TODO: throw KareError.INVALID_EXERCISE
            }
        }

        for (ExerciseDto exercise : exercises) {
            try {

                //Entry in DB exists -> update
                Exercise dbEntry = exerciseRepository.findByExerciseIdAndWorkoutId(exercise.id(), exercise.workoutId())
                        .orElseThrow(() -> new NoSuchElementException( //TODO: throw KareError.EXERCISE_NOT_FOUND
                                String.format("No exercise found with exerciseId %d and workoutId %d", exercise.id(), exercise.workoutId())
                        ));

                updateExercise(exerciseMapper.toDto(dbEntry), exercise);
            }catch (NoSuchElementException e){

                //Exercise not found -> new entry...
                insertExerciseAndSets(exercise, exercise.sets());
            }
        }


        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            //TODO: throw KareError.WORKOUT_DETAILS_NOT_FOUND
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto deleteExercise(Long workoutId, Long exerciseId) {

        //If exercise is not found error will be thrown
        Exercise exercise = exerciseRepository.findByExerciseIdAndWorkoutId(exerciseId, workoutId)
                .orElseThrow(() -> new NoSuchElementException( //TODO: throw KareError.EXERCISE_NOT_FOUND
                                String.format("No exercise found with exerciseId %d and workoutId %d", exerciseId, workoutId)
                        )
                );

        exerciseRepository.delete(exercise);

        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);

        //Validation
        if (workoutDetails == null) {
            //TODO: throw KareError.WORKOUT_DETAILS_NOT_FOUND
        }

        //After delete the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetails);
    }

    @Override
    public WorkoutDetailsDto deleteMultipleExercises(Long workoutId, List<Long> exerciseIds) {

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            //TODO: throw KareError.WORKOUT_DETAILS_NOT_FOUND
        }

        for (Long exerciseId : exerciseIds) {
            Exercise exercise = exerciseRepository.findByExerciseIdAndWorkoutId(exerciseId, workoutId)
                    .orElseThrow(() -> new NoSuchElementException( //TODO: throw KareError.EXERCISE_NOT_FOUND
                                    String.format("No exercise found with exerciseId %d and workoutId %d", exerciseId, workoutId)
                            )
                    );
            exerciseRepository.delete(exercise);
        }

        //After delete the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetails);
    }


    /**
     * Helper functions
     */
    private void insertExerciseAndSets(ExerciseDto exercise, List<ExerciseSetDto> sets) {
        Exercise updatedExercise = exerciseMapper.toEntity(exercise);

        //Updates workoutId just in case it is different from provided one
//        updatedExercise.setWorkoutId(workoutId);

        List<ExerciseSet> updatedSets = sets
                .stream()
                .map(exerciseSetMapper::toEntity)
                .toList();

        exerciseRepository.save(updatedExercise);

        updatedSets.forEach(set -> {
            set.setWorkoutId(updatedExercise.getWorkoutId());
            exerciseSetRepository.save(set);
        });

//        exerciseSetRepository.saveAll(sets);
    }

    private void updateExercise(ExerciseDto dbEntry, ExerciseDto exercise){
        //Update exercise
//        exerciseRepository.updateExercise(exercise);

        //Delete old sets
        dbEntry.sets()
                .forEach(
                set -> exerciseSetRepository.deleteByExerciseSetId(set.id())
        );

        //Add new sets
        exercise.sets()
                .stream()
                .map(exerciseSetMapper::toEntity)
                .forEach(exerciseSetRepository::save);
    }
}
