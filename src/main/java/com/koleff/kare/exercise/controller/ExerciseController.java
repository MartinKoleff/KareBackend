package com.koleff.kare.exercise.controller;

import com.koleff.kare.common.Constants;
import com.koleff.kare.exercise.models.dto.ExerciseDetailsDto;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.request.AddNewExerciseSetRequest;
import com.koleff.kare.exercise.models.request.DeleteExerciseSetRequest;
import com.koleff.kare.exercise.models.request.FetchExerciseRequest;
import com.koleff.kare.exercise.models.request.FetchExercisesByMuscleGroupRequest;
import com.koleff.kare.exercise.models.response.ExerciseDetailsResponse;
import com.koleff.kare.exercise.models.response.ExerciseListResponse;
import com.koleff.kare.exercise.models.response.ExerciseResponse;
import com.koleff.kare.exercise.service.ExerciseConfigureService;
import com.koleff.kare.exercise.service.ExerciseDetailsService;
import com.koleff.kare.exercise.service.ExerciseService;
import com.koleff.kare.exercise.service.ExerciseSetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ExerciseSetService exerciseSetService;
    private final ExerciseDetailsService exerciseDetailsService;
    private final ExerciseConfigureService exerciseConfigureService;

    public ExerciseController(
            ExerciseService exerciseService,
            ExerciseSetService exerciseSetService,
            ExerciseDetailsService exerciseDetailsService,
            ExerciseConfigureService exerciseConfigureService
    ) {
        this.exerciseService = exerciseService;
        this.exerciseSetService = exerciseSetService;
        this.exerciseDetailsService = exerciseDetailsService;
        this.exerciseConfigureService = exerciseConfigureService;
    }

    @PostMapping("/getcatalogexercises/all")
    public ExerciseListResponse getCatalogExercises(@RequestBody FetchExercisesByMuscleGroupRequest request) {
        List<ExerciseDto> exercises = exerciseService.getCatalogExercises(request.muscleGroupId());

        return new ExerciseListResponse(
                exercises,
                true,
                null
        );
    }

    @PostMapping("/getcatalogexercise")
    public ExerciseResponse getCatalogExercise(@RequestBody FetchExerciseRequest request) {
        ExerciseDto exercise = exerciseService.getExercise(request.exerciseId(), Constants.CATALOG_WORKOUT_ID);

        return new ExerciseResponse(
                exercise,
                true,
                null
        );
    }

    @PostMapping("/getexercise")
    public ExerciseResponse getExercise(@RequestBody FetchExerciseRequest request) {
        ExerciseDto exercise = exerciseService.getExercise(request.exerciseId(), request.workoutId());

        return new ExerciseResponse(
                exercise,
                true,
                null
        );
    }

    @PostMapping("/getexercisedetails")
    public ExerciseDetailsResponse getExerciseDetails(@RequestBody FetchExerciseRequest request) {
        ExerciseDetailsDto exerciseDetails = exerciseDetailsService.getExerciseDetails(request.exerciseId(), request.workoutId());

        return new ExerciseDetailsResponse(
                exerciseDetails,
                true,
                null
        );
    }

//    //Use only in local datasource in client...
//
//    @PostMapping("/addnewexerciseset")
//    public ExerciseResponse addNewExerciseSet(@RequestBody AddNewExerciseSetRequest request) {
//        ExerciseDto exercise = exerciseConfigureService.addNewExerciseSet(request.exerciseId(), request.workoutId(), request.currentSets());
//
//        return new ExerciseResponse(exercise);
//    }
//
//    @PostMapping("/deleteexerciseset")
//    public ExerciseResponse deleteExerciseSet(@RequestBody DeleteExerciseSetRequest request) {
//        ExerciseDto exercise = exerciseConfigureService.deleteExerciseSet(request.exerciseId(), request.workoutId(), request.setId());
//
//        return new ExerciseResponse(exercise);
//    }
}