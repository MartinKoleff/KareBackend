package com.koleff.kare.workout.service;

import com.koleff.kare.common.error.exceptions.ExerciseNotFoundException;
import com.koleff.kare.common.error.exceptions.InvalidExerciseException;
import com.koleff.kare.common.error.exceptions.WorkoutDetailsNotFoundException;
import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.mapper.ExerciseSetMapper;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.exercise.repository.ExerciseRepository;
import com.koleff.kare.exercise.repository.ExerciseSetRepository;
import com.koleff.kare.workout.mapper.WorkoutDetailsMapper;
import com.koleff.kare.workout.mapper.WorkoutMapper;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.entity.Workout;
import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import com.koleff.kare.workout.models.entity.WorkoutDetails;
import com.koleff.kare.workout.repository.WorkoutConfigurationRepository;
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
    private final WorkoutConfigurationRepository workoutConfigurationRepository;

    @Autowired
    public WorkoutCustomServiceImpl(
            WorkoutRepository workoutRepository,
            WorkoutMapper workoutMapper,
            WorkoutDetailsRepository workoutDetailsRepository,
            WorkoutDetailsMapper workoutDetailsMapper,
            ExerciseMapper exerciseMapper,
            ExerciseSetMapper exerciseSetMapper,
            ExerciseRepository exerciseRepository,
            ExerciseSetRepository exerciseSetRepository,
            WorkoutConfigurationRepository workoutConfigurationRepository
    ) {
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
        this.workoutDetailsRepository = workoutDetailsRepository;
        this.workoutDetailsMapper = workoutDetailsMapper;
        this.exerciseMapper = exerciseMapper;
        this.exerciseSetMapper = exerciseSetMapper;
        this.exerciseRepository = exerciseRepository;
        this.exerciseSetRepository = exerciseSetRepository;
        this.workoutConfigurationRepository = workoutConfigurationRepository;
    }

    @Override
    public WorkoutDto createNewWorkout() {
        Workout workout = new Workout();

        Long workoutId = workoutRepository.save(workout).getWorkoutId();
        String workoutName = "Workout " + workoutId;

        //Update workout
        workout.setWorkoutId(workoutId);
        workout.setName(workoutName);

        workoutRepository.updateWorkout(
                workout.getName(),
                workout.getMuscleGroupId(),
                workout.getSnapshot(),
                workout.getTotalExercises(),
                workout.getIsFavorite(),
                workout.getWorkoutId()
        );

        //Create workout details
        WorkoutDetails workoutDetails = new WorkoutDetails();
        workoutDetails.setWorkoutDetailsId(workoutId);
        workoutDetails.setName(workoutName);

        Long workoutDetailsId = workoutDetailsRepository.save(workoutDetails).getWorkoutDetailsId();

        //Create workout configuration
        WorkoutConfiguration workoutConfiguration = new WorkoutConfiguration();
        workoutConfiguration.setWorkoutDetailsId(workoutId);

        workoutConfigurationRepository.save(workoutConfiguration);

        return workoutMapper.toDto(workout);
    }

    @Override
    public WorkoutDto createCustomWorkout(WorkoutDto workoutDto) {
        Workout updatedWorkout = workoutMapper.toEntity(workoutDto);

        Long workoutId = workoutRepository.save(updatedWorkout).getWorkoutId();
        updatedWorkout.setWorkoutId(workoutId);

        //Update workout
        workoutRepository.updateWorkout(
                updatedWorkout.getName(),
                updatedWorkout.getMuscleGroupId(),
                updatedWorkout.getSnapshot(),
                updatedWorkout.getTotalExercises(),
                updatedWorkout.getIsFavorite(),
                updatedWorkout.getWorkoutId()
        );

        //Create workout details
        WorkoutDetails workoutDetails = new WorkoutDetails(); //TODO: create constructor...
        workoutDetails.setWorkoutDetailsId(workoutId);
        workoutDetails.setName(workoutDto.name());
        workoutDetails.setDescription("");
        workoutDetails.setIsFavorite(workoutDto.isFavorite());

        //TODO: check if workout details entry exists in DB...
        Long workoutDetailsId = workoutDetailsRepository.save(workoutDetails).getWorkoutDetailsId();

        //Create workout configuration
        WorkoutConfiguration workoutConfiguration = new WorkoutConfiguration();
        workoutConfiguration.setWorkoutDetailsId(workoutId);

        workoutConfigurationRepository.save(workoutConfiguration);

        return workoutMapper.toDto(updatedWorkout);
    }

    @Override
    public WorkoutDetailsDto createCustomWorkoutDetails(WorkoutDetailsDto workoutDetailsDto) {
        WorkoutDetails updatedWorkoutDetails = workoutDetailsMapper.toEntity(workoutDetailsDto);

        Long workoutDetailsId = workoutDetailsRepository.save(updatedWorkoutDetails).getWorkoutDetailsId();
        updatedWorkoutDetails.setWorkoutDetailsId(workoutDetailsId);

        //Create workout
        Workout workout = new Workout(); //TODO: create constructor...
        workout.setWorkoutId(workoutDetailsId);
        workout.setName(updatedWorkoutDetails.getName());
        workout.setTotalExercises(updatedWorkoutDetails.getExercises().size());
        workout.setIsFavorite(updatedWorkoutDetails.getIsFavorite());
        workout.setSnapshot("");

        //TODO: check if workout entry exists in DB...
        Long workoutId = workoutRepository.save(workout).getWorkoutId();

        //Save exercises
        workoutDetailsDto.exercises().forEach(this::insertExerciseAndSets);

        //Create workout configuration
        WorkoutConfiguration workoutConfiguration = new WorkoutConfiguration();
        workoutConfiguration.setWorkoutDetailsId(workoutDetailsId);

        workoutConfigurationRepository.save(workoutConfiguration);

        return workoutDetailsMapper.toDto(updatedWorkoutDetails);
    }

    @Override
    public WorkoutDetailsDto addExercise(Long workoutId, ExerciseDto exercise) throws InvalidExerciseException, WorkoutDetailsNotFoundException {

        //Validation
        if (exercise.workoutId() == -1 || !exercise.workoutId().equals(workoutId)) {
            throw new InvalidExerciseException();
        }

        insertExerciseAndSets(exercise);

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            throw new WorkoutDetailsNotFoundException();
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto addMultipleExercises(Long workoutId, List<ExerciseDto> exercises) throws InvalidExerciseException, WorkoutDetailsNotFoundException {

        //Validation
        for (ExerciseDto exercise : exercises) {
            if (!exercise.workoutId().equals(workoutId)) {
                throw new InvalidExerciseException();
            }
        }

        for (ExerciseDto exercise : exercises) {
            insertExerciseAndSets(exercise);
        }

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            throw new WorkoutDetailsNotFoundException();
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto submitExercise(Long workoutId, ExerciseDto exercise) throws InvalidExerciseException, WorkoutDetailsNotFoundException {
        //Validation
        if (exercise.workoutId() == -1 || !exercise.workoutId().equals(workoutId)) {
            throw new InvalidExerciseException();
        }

        try {

            //Entry in DB exists -> update
            Exercise dbEntry = exerciseRepository.findByExerciseIdAndWorkoutId(exercise.id(), exercise.workoutId())
                    .orElseThrow(ExerciseNotFoundException::new);

            updateExercise(exerciseMapper.toDto(dbEntry), exercise);
        } catch (ExerciseNotFoundException e) {

            //Exercise not found -> new entry...
            insertExerciseAndSets(exercise);
        }

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            throw new WorkoutDetailsNotFoundException();
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto submitMultipleExercises(Long workoutId, List<ExerciseDto> exercises)  throws InvalidExerciseException, WorkoutDetailsNotFoundException {
        //Validation
        for (ExerciseDto exercise : exercises) {
            if (!exercise.workoutId().equals(workoutId)) {
                throw new InvalidExerciseException();
            }
        }

        for (ExerciseDto exercise : exercises) {
            try {

                //Entry in DB exists -> update
                Exercise dbEntry = exerciseRepository.findByExerciseIdAndWorkoutId(exercise.id(), exercise.workoutId())
                        .orElseThrow(ExerciseNotFoundException::new);

                updateExercise(exerciseMapper.toDto(dbEntry), exercise);
            } catch (NoSuchElementException e) {

                //Exercise not found -> new entry...
                insertExerciseAndSets(exercise);
            }
        }


        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            throw new WorkoutDetailsNotFoundException();
        }

        //After insert the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetailsWithSets = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetailsWithSets);
    }

    @Override
    public WorkoutDetailsDto deleteExercise(Long workoutId, Long exerciseId) throws ExerciseNotFoundException, WorkoutDetailsNotFoundException{

        //If exercise is not found error will be thrown
        Exercise exercise = exerciseRepository.findByExerciseIdAndWorkoutId(exerciseId, workoutId)
                .orElseThrow(ExerciseNotFoundException::new);

        exerciseRepository.delete(exercise);

        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);

        //Validation
        if (workoutDetails == null) {
            throw new WorkoutDetailsNotFoundException();
        }

        //After delete the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetails);
    }

    @Override
    public WorkoutDetailsDto deleteMultipleExercises(Long workoutId, List<Long> exerciseIds) throws ExerciseNotFoundException, WorkoutDetailsNotFoundException{

        //Validation
        WorkoutDetails workoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        if (workoutDetails == null) {
            throw new WorkoutDetailsNotFoundException();
        }

        for (Long exerciseId : exerciseIds) {
            Exercise exercise = exerciseRepository.findByExerciseIdAndWorkoutId(exerciseId, workoutId)
                    .orElseThrow(ExerciseNotFoundException::new);

            exerciseRepository.delete(exercise);
        }

        //After delete the relations will be updated in DB...
        WorkoutDetails updatedWorkoutDetails = workoutDetailsRepository.getWorkoutDetailsByWorkoutDetailsId(workoutId);
        return workoutDetailsMapper.toDto(updatedWorkoutDetails);
    }


    /**
     * Helper functions
     */
    private void insertExerciseAndSets(ExerciseDto exercise) {
        Exercise updatedExercise = exerciseMapper.toEntity(exercise);

        //Updates workoutId just in case it is different from provided one
//        updatedExercise.setWorkoutId(workoutId);

        List<ExerciseSet> updatedSets = exercise.sets()
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

    private void updateExercise(ExerciseDto dbEntry, ExerciseDto exercise) {

        //Update exercise
//        exerciseRepository.updateExercise(
//                exercise.name(),
//                exercise.muscleGroupId(),
//                exercise.machineTypeId(),
//                exercise.snapshot(),
//                exercise.id(),
//                exercise.workoutId()
//        );

        //Delete old sets
        dbEntry.sets()
                .forEach(set -> exerciseSetRepository.deleteByExerciseSetId(set.id().toString()));

        //Add new sets
        exercise.sets()
                .stream()
                .map(exerciseSetMapper::toEntity)
                .forEach(exerciseSetRepository::save);
    }
}
