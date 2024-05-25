package com.koleff.kare.do_workout_performance_metrics.controller;

import com.koleff.kare.common.base_response.BaseResponse;
import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutPerformanceMetricsDto;
import com.koleff.kare.do_workout_performance_metrics.models.request.*;
import com.koleff.kare.do_workout_performance_metrics.models.response.DoWorkoutPerformanceMetricsListResponse;
import com.koleff.kare.do_workout_performance_metrics.models.response.DoWorkoutPerformanceMetricsResponse;
import com.koleff.kare.do_workout_performance_metrics.services.DoWorkoutExerciseSetService;
import com.koleff.kare.do_workout_performance_metrics.services.DoWorkoutPerformanceMetricsService;
import com.koleff.kare.workout.models.request.FetchWorkoutByWorkoutIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/doworkoutperformancemetrics/")
public class DoWorkoutPerformanceMetricsController {

    private final DoWorkoutPerformanceMetricsService doWorkoutPerformanceMetricsService;
    private final DoWorkoutExerciseSetService doWorkoutExerciseSetService;

    @Autowired
    public DoWorkoutPerformanceMetricsController(
            DoWorkoutPerformanceMetricsService doWorkoutPerformanceMetricsService,
            DoWorkoutExerciseSetService doWorkoutExerciseSetService
    ) {
        this.doWorkoutPerformanceMetricsService = doWorkoutPerformanceMetricsService;
        this.doWorkoutExerciseSetService = doWorkoutExerciseSetService;
    }

    @GetMapping("/getalldoworkoutperformancemetricsbydateandid")
    public DoWorkoutPerformanceMetricsListResponse getAllDoWorkoutPerformanceMetrics(
            @RequestBody FetchPerformanceMetricsByDateAndIdRequest request
    ) {
        List<DoWorkoutPerformanceMetricsDto> performanceMetricsList = doWorkoutPerformanceMetricsService.getAllDoWorkoutPerformanceMetrics(
                request.performanceMetricsId(),
                request.start(),
                request.end()
        );

        return new DoWorkoutPerformanceMetricsListResponse(
                performanceMetricsList,
                true,
                null
        );
    }

    @GetMapping("/getalldoworkoutperformancemetricsbyid")
    public DoWorkoutPerformanceMetricsResponse getAllDoWorkoutPerformanceMetrics(
            @RequestBody FetchPerformanceMetricsByIdRequest request
    ) {
        DoWorkoutPerformanceMetricsDto performanceMetrics = doWorkoutPerformanceMetricsService.getDoWorkoutPerformanceMetricsById(
                request.performanceMetricsId()
        );

        return new DoWorkoutPerformanceMetricsResponse(
                performanceMetrics,
                true,
                null
        );
    }

    @GetMapping("/getalldoworkoutperformancemetricsbyworkoutid")
    public DoWorkoutPerformanceMetricsListResponse getAllDoWorkoutPerformanceMetrics(
            @RequestBody FetchPerformanceMetricsByDateAndWorkoutIdRequest request
    ) {
        List<DoWorkoutPerformanceMetricsDto> performanceMetricsList = doWorkoutPerformanceMetricsService.getAllDoWorkoutPerformanceMetricsByWorkoutId(
                request.workoutId(),
                request.start(),
                request.end()
        );

        return new DoWorkoutPerformanceMetricsListResponse(
                performanceMetricsList,
                true,
                null
        );
    }

    @GetMapping("/getalldoworkoutperformancemetrics")
    public DoWorkoutPerformanceMetricsListResponse getAllDoWorkoutPerformanceMetrics() {
        List<DoWorkoutPerformanceMetricsDto> performanceMetricsList = doWorkoutPerformanceMetricsService.getAllDoWorkoutPerformanceMetrics();

        return new DoWorkoutPerformanceMetricsListResponse(
                performanceMetricsList,
                true,
                null
        );
    }

    @GetMapping("/getalldoworkoutperformancemetricsbydate")
    public DoWorkoutPerformanceMetricsListResponse getAllDoWorkoutPerformanceMetrics(
            @RequestBody FetchPerformanceMetricsByDateRequest request
    ) {
        List<DoWorkoutPerformanceMetricsDto> performanceMetricsList = doWorkoutPerformanceMetricsService.getAllDoWorkoutPerformanceMetrics(
                request.start(),
                request.end()
        );

        return new DoWorkoutPerformanceMetricsListResponse(
                performanceMetricsList,
                true,
                null
        );
    }

    @GetMapping("/getdoworkoutperformancemetrics")
    public DoWorkoutPerformanceMetricsListResponse getDoWorkoutPerformanceMetrics(
            @RequestBody FetchWorkoutByWorkoutIdRequest request
    ) {
        List<DoWorkoutPerformanceMetricsDto> performanceMetricsList = doWorkoutPerformanceMetricsService.getDoWorkoutPerformanceMetricsByWorkoutId(
                request.workoutId()
        );

        return new DoWorkoutPerformanceMetricsListResponse(
                performanceMetricsList,
                true,
                null
        );
    }

    @PostMapping("/savedoworkoutperformancemetrics")
    public DoWorkoutPerformanceMetricsResponse saveDoWorkoutPerformanceMetrics(
            @RequestBody UpdatePerformanceMetricsRequest request
    ) {
        DoWorkoutPerformanceMetricsDto performanceMetrics = doWorkoutPerformanceMetricsService.saveDoWorkoutPerformanceMetrics(
                request.performanceMetrics()
        );

        return new DoWorkoutPerformanceMetricsResponse(
                performanceMetrics,
                true,
                null
        );
    }

    @PutMapping("/updatedoworkoutperformancemetrics")
    public DoWorkoutPerformanceMetricsResponse updateDoWorkoutPerformanceMetrics(
            @RequestBody UpdatePerformanceMetricsRequest request
    ) {
        DoWorkoutPerformanceMetricsDto performanceMetrics = doWorkoutPerformanceMetricsService.updateDoWorkoutPerformanceMetrics(
                request.performanceMetrics()
        );

        return new DoWorkoutPerformanceMetricsResponse(
                performanceMetrics,
                true,
                null
        );
    }

    @DeleteMapping("/deletedoworkoutperformancemetrics")
    public BaseResponse deleteDoWorkoutPerformanceMetrics(
            @RequestBody FetchPerformanceMetricsByIdRequest request
    ) {
        doWorkoutPerformanceMetricsService.deleteDoWorkoutPerformanceMetrics(
                request.performanceMetricsId()
        );

        return new BaseResponse(
                true,
                null
        );
    }

    @PostMapping("/savedoworkoutexerciseset")
    public BaseResponse saveDoWorkoutExerciseSet(
            @RequestBody SaveDoWorkoutExerciseSetRequest request
    ) {
        doWorkoutExerciseSetService.saveDoWorkoutExerciseSet(
                request.doWorkoutExerciseSet()
        );

        return new BaseResponse(
                true,
                null
        );
    }

    @PostMapping("/savealldoworkoutexercisesets")
    public BaseResponse saveAllDoWorkoutExerciseSets(
            @RequestBody SaveMultipleDoWorkoutExerciseRequest request
    ) {
        doWorkoutExerciseSetService.saveAllDoWorkoutExerciseSet(
                request.doWorkoutExerciseSetList()
        );

        return new BaseResponse(
                true,
                null
        );
    }
}
