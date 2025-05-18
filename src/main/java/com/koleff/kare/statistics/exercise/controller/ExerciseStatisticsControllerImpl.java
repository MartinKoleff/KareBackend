package com.koleff.kare.statistics.exercise.controller;

import com.koleff.kare.auth.models.dto.UserDto;
import com.koleff.kare.auth.models.request.AuthenticationRequest;
import com.koleff.kare.auth.models.request.LogoutRequest;
import com.koleff.kare.auth.models.response.AuthenticationResponse;
import com.koleff.kare.auth.models.response.RegistrationResponse;
import com.koleff.kare.auth.service.AuthenticationServiceImpl;
import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.statistics.exercise.models.request.ExerciseStatisticsRequest;
import com.koleff.kare.statistics.exercise.models.response.ExercisePRResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalRepsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalSetsResponse;
import com.koleff.kare.statistics.exercise.models.response.TotalWeightLiftedResponse;
import com.koleff.kare.statistics.exercise.service.ExerciseStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/statistics/exercise")
@CrossOrigin("*")
public class ExerciseStatisticsControllerImpl implements ExerciseStatisticsController {

    private final ExerciseStatisticsService exerciseStatisticsService;

    @Autowired
    public ExerciseStatisticsControllerImpl(ExerciseStatisticsService exerciseStatisticsService) {
        this.exerciseStatisticsService = exerciseStatisticsService;
    }

    @GetMapping("/getpr")
    @Override
    public ExercisePRResponse getBestPR(@RequestBody ExerciseStatisticsRequest request) {
        Float pr = exerciseStatisticsService.getPR(
                request.exerciseId()
        );

        //TODO: refactor responses to be like auth package?
        return new ExercisePRResponse(
                pr,
                true,
                null
        );
    }

    @GetMapping("/get1repmax")
    @Override
    public ExercisePRResponse get1RepMax(@RequestBody ExerciseStatisticsRequest request) {
        Float pr = exerciseStatisticsService.get1RepMax(
                request.exerciseId()
        );

        return new ExercisePRResponse(
                pr,
                true,
                null
        );
    }

    @GetMapping("/gettotalrepsperformed")
    @Override
    public TotalRepsResponse getTotalRepsPerformed(@RequestBody ExerciseStatisticsRequest request) {
        Integer totalReps = exerciseStatisticsService.getTotalRepsPerformed(
                request.exerciseId()
        );

        return new TotalRepsResponse(
                totalReps,
                true,
                null
        );
    }

    @GetMapping("/gettotalsetsperformed")
    @Override
    public TotalSetsResponse getTotalSetsPerformed(@RequestBody ExerciseStatisticsRequest request) {
        Integer totalSets = exerciseStatisticsService.getTotalSetsPerformed(
                request.exerciseId()
        );

        return new TotalSetsResponse(
                totalSets,
                true,
                null
        );
    }

    @GetMapping("/gettotalweightlifted")
    @Override
    public TotalWeightLiftedResponse getTotalWeightLifted(@RequestBody ExerciseStatisticsRequest request) {
        Float totalWeight = exerciseStatisticsService.getTotalWeightLifted(
                request.exerciseId()
        );

        return new TotalWeightLiftedResponse(
                totalWeight,
                true,
                null
        );
    }
}
