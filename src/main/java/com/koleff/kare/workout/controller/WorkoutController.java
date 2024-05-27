package com.koleff.kare.workout.controller;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.workout.models.dto.WorkoutConfigurationDto;
import com.koleff.kare.workout.models.dto.WorkoutDetailsDto;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.entity.WorkoutConfiguration;
import com.koleff.kare.workout.models.request.*;
import com.koleff.kare.workout.models.response.*;
import com.koleff.kare.workout.service.WorkoutConfigurationService;
import com.koleff.kare.workout.service.WorkoutCustomService;
import com.koleff.kare.workout.service.WorkoutDetailsService;
import com.koleff.kare.workout.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final WorkoutDetailsService workoutDetailsService;
    private final WorkoutCustomService workoutCustomService;
    private final WorkoutConfigurationService workoutConfigurationService;

    @Autowired
    public WorkoutController(
            WorkoutService workoutService,
            WorkoutDetailsService workoutDetailsService,
            WorkoutCustomService workoutCustomService,
            WorkoutConfigurationService workoutConfigurationService
    ) {
        this.workoutService = workoutService;
        this.workoutDetailsService = workoutDetailsService;
        this.workoutCustomService = workoutCustomService;
        this.workoutConfigurationService = workoutConfigurationService;
    }

    @PutMapping("/favoriteworkout")
    public BaseResponse favoriteWorkout(@RequestBody FetchWorkoutByWorkoutIdRequest request) {
        workoutService.favoriteWorkout(request.workoutId());

        return new BaseResponse(
                true,
                null
        );
    }

    @PutMapping("/unfavoriteworkout")
    public BaseResponse unfavoriteWorkout(@RequestBody FetchWorkoutByWorkoutIdRequest request) {
        workoutService.unfavoriteWorkout(request.workoutId());

        return new BaseResponse(
                true,
                null
        );
    }

    @GetMapping("/getfavoriteworkouts")
    public WorkoutListResponse getFavoriteWorkouts() {
        List<WorkoutDto> favoriteWorkouts = workoutService.getFavoriteWorkouts();

        return new WorkoutListResponse(
                favoriteWorkouts,
                true,
                null
        );
    }

    @GetMapping("/getworkout")
    public WorkoutResponse getWorkout(@RequestBody FetchWorkoutByWorkoutIdRequest request) {
        WorkoutDto workout = workoutService.getWorkout(request.workoutId());

        return new WorkoutResponse(
                workout,
                true,
                null
        );
    }

    @GetMapping("/getallworkouts")
    public WorkoutListResponse getAllWorkouts() {
        List<WorkoutDto> workouts = workoutService.getWorkoutsOrderedById();

        return new WorkoutListResponse(
                workouts,
                true,
                null
        );
    }

    @GetMapping("/getallworkoutdetails")
    public WorkoutDetailsListResponse getAllWorkoutDetails() {
        List<WorkoutDetailsDto> workoutDetailsList = workoutDetailsService.getWorkoutDetailsOrderedById();

        return new WorkoutDetailsListResponse(
                workoutDetailsList,
                true,
                null
        );
    }

    @GetMapping("/getworkoutdetails")
    public WorkoutDetailsResponse getWorkoutDetails(@RequestBody FetchWorkoutByWorkoutIdRequest request) {
        WorkoutDetailsDto workoutDetails = workoutDetailsService.getWorkoutDetails(request.workoutId());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @DeleteMapping("/deleteworkout")
    public BaseResponse deleteWorkout(@RequestBody FetchWorkoutByWorkoutIdRequest request) {
        workoutService.deleteWorkout(request.workoutId());

        return new BaseResponse(
                true,
                null
        );
    }

    @DeleteMapping("/deleteexercise")
    public WorkoutDetailsResponse deleteWorkout(@RequestBody ExerciseDeletionRequest request) {
        WorkoutDetailsDto workoutDetails = workoutCustomService.deleteExercise(request.workoutId(), request.exerciseId());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @DeleteMapping("/deletemultipleexercises")
    public WorkoutDetailsResponse deleteMultipleExercises(@RequestBody MultipleExercisesDeletionRequest request) {
        WorkoutDetailsDto workoutDetails = workoutCustomService.deleteMultipleExercises(request.workoutId(), request.exerciseIds());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @PostMapping("/addexercise")
    public WorkoutDetailsResponse addExercise(@RequestBody ExerciseAddRequest request) {
        WorkoutDetailsDto workoutDetails = workoutCustomService.addExercise(request.workoutId(), request.exercise());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @PostMapping("/addmultipleexercises")
    public WorkoutDetailsResponse addMultipleExercises(@RequestBody MultipleExercisesUpdateRequest request) {
        WorkoutDetailsDto workoutDetails = workoutCustomService.addMultipleExercises(request.workoutId(), request.exerciseList());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @PostMapping("/submitexercise")
    public WorkoutDetailsResponse submitExercise(@RequestBody ExerciseAddRequest request) {
        WorkoutDetailsDto workoutDetails = workoutCustomService.submitExercise(request.workoutId(), request.exercise());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @PostMapping("/submitmultipleexercises")
    public WorkoutDetailsResponse submitMultipleExercises(@RequestBody MultipleExercisesUpdateRequest request) {
        WorkoutDetailsDto workoutDetails = workoutCustomService.submitMultipleExercises(request.workoutId(), request.exerciseList());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @PutMapping("/updateworkoutdetails")
    public BaseResponse updateWorkoutDetails(@RequestBody UpdateWorkoutDetailsRequest request) {
        workoutDetailsService.updateWorkoutDetails(request.workoutDetails());

        return new BaseResponse(
                true,
                null
        );
    }

    @PutMapping("/updateworkout")
    public BaseResponse updateWorkout(@RequestBody UpdateWorkoutRequest request) {
        workoutService.updateWorkout(request.workout());

        return new BaseResponse(
                true,
                null
        );
    }

    @PostMapping("/createnewworkout")
    public WorkoutResponse createNewWorkout() {
        WorkoutDto workout = workoutCustomService.createNewWorkout();

        return new WorkoutResponse(
                workout,
                true,
                null
        );
    }

    @PostMapping("/createcustomworkout")
    public WorkoutResponse createCustomWorkout(@RequestBody UpdateWorkoutRequest request) {
        WorkoutDto workout = workoutCustomService.createCustomWorkout(request.workout());

        return new WorkoutResponse(
                workout,
                true,
                null
        );
    }

    @PostMapping("/createcustomworkoutdetails")
    public WorkoutDetailsResponse createCustomWorkoutDetails(@RequestBody UpdateWorkoutDetailsRequest request) {
        WorkoutDetailsDto workoutDetails = workoutCustomService.createCustomWorkoutDetails(request.workoutDetails());

        return new WorkoutDetailsResponse(
                workoutDetails,
                true,
                null
        );
    }

    @GetMapping("/getworkoutconfiguration")
    public WorkoutConfigurationResponse getWorkoutConfiguration(@RequestBody FetchWorkoutByWorkoutIdRequest request) {
        WorkoutConfigurationDto workoutConfiguration = workoutConfigurationService.getWorkoutConfiguration(request.workoutId());

        return new WorkoutConfigurationResponse(
                workoutConfiguration,
                true,
                null
        );
    }

    @PutMapping("/updateworkoutconfiguration")
    public BaseResponse updateWorkoutConfiguration(@RequestBody FetchWorkoutConfigurationRequest request) {
        workoutConfigurationService.updateWorkoutConfiguration(request.workoutConfiguration());

        return new BaseResponse(
                true,
                null
        );
    }

    @PostMapping("/saveworkoutconfiguration")
    public WorkoutConfigurationResponse saveWorkoutConfiguration(@RequestBody FetchWorkoutConfigurationRequest request) {
        WorkoutConfigurationDto workoutConfiguration = workoutConfigurationService.saveWorkoutConfiguration(request.workoutConfiguration());

        return new WorkoutConfigurationResponse(
                workoutConfiguration,
                true,
                null
        );
    }

    @DeleteMapping("/deleteworkoutconfiguration")
    public BaseResponse deleteWorkoutConfiguration(@RequestBody FetchWorkoutByWorkoutIdRequest request) {
        workoutConfigurationService.deleteWorkoutConfiguration(request.workoutId());

        return new BaseResponse(
                true,
                null
        );
    }
}
