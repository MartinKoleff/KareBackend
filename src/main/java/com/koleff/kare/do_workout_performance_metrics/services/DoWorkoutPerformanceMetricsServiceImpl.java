package com.koleff.kare.do_workout_performance_metrics.services;

import com.koleff.kare.common.error.exceptions.DoWorkoutPerformanceMetricsNotFoundException;
import com.koleff.kare.do_workout_performance_metrics.mapper.DoWorkoutPerformanceMetricsMapper;
import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutPerformanceMetricsDto;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutPerformanceMetrics;
import com.koleff.kare.do_workout_performance_metrics.repository.DoWorkoutPerformanceMetricsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DoWorkoutPerformanceMetricsServiceImpl implements DoWorkoutPerformanceMetricsService {

    private final DoWorkoutPerformanceMetricsRepository doWorkoutPerformanceMetricsRepository;
    private final DoWorkoutPerformanceMetricsMapper doWorkoutPerformanceMetricsMapper;
    private final static Logger logger = LogManager.getLogger(DoWorkoutPerformanceMetricsServiceImpl.class);

    @Autowired
    public DoWorkoutPerformanceMetricsServiceImpl(
            DoWorkoutPerformanceMetricsRepository doWorkoutPerformanceMetricsRepository,
            DoWorkoutPerformanceMetricsMapper doWorkoutPerformanceMetricsMapper
    ) {
        this.doWorkoutPerformanceMetricsRepository = doWorkoutPerformanceMetricsRepository;
        this.doWorkoutPerformanceMetricsMapper = doWorkoutPerformanceMetricsMapper;
    }

    @Override
    public DoWorkoutPerformanceMetricsDto saveDoWorkoutPerformanceMetrics(DoWorkoutPerformanceMetricsDto performanceMetricsDto) {
        DoWorkoutPerformanceMetrics updatedPerformanceMetrics = doWorkoutPerformanceMetricsMapper.toEntity(performanceMetricsDto);

        Long id = doWorkoutPerformanceMetricsRepository.save(updatedPerformanceMetrics).getId();
        updatedPerformanceMetrics.setId(id);

        return doWorkoutPerformanceMetricsMapper.toDto(updatedPerformanceMetrics);
    }

    @Override
    public List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetrics(Long id, Date start, Date end) {
        List<DoWorkoutPerformanceMetrics> performanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetricsById(id, start, end);

        return performanceMetricsList.stream()
                .map(doWorkoutPerformanceMetricsMapper::toDto)
                .toList();
    }

    @Override
    public DoWorkoutPerformanceMetricsDto getDoWorkoutPerformanceMetricsById(Long id) throws DoWorkoutPerformanceMetricsNotFoundException {
        DoWorkoutPerformanceMetrics performanceMetrics = doWorkoutPerformanceMetricsRepository.findWorkoutPerformanceMetricsById(id);

        if(performanceMetrics == null) throw new DoWorkoutPerformanceMetricsNotFoundException();

        return doWorkoutPerformanceMetricsMapper.toDto(performanceMetrics);
    }

    @Override
    public List<DoWorkoutPerformanceMetricsDto> getDoWorkoutPerformanceMetricsByWorkoutId(Long workoutId) {
        List<DoWorkoutPerformanceMetrics> performanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetricsByWorkoutId(workoutId);

        return performanceMetricsList.stream()
                .map(doWorkoutPerformanceMetricsMapper::toDto)
                .toList();
    }

    @Override
    public List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetricsByWorkoutId(Long workoutId, Date start, Date end) {
        List<DoWorkoutPerformanceMetrics> performanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetricsByWorkoutId(workoutId, start, end);

        return performanceMetricsList.stream()
                .map(doWorkoutPerformanceMetricsMapper::toDto)
                .toList();
    }

    @Override
    public List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetrics() {
        List<DoWorkoutPerformanceMetrics> performanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics();

        performanceMetricsList.forEach(
                performanceMetrics -> {
                    logger.info("performance metrics: " + performanceMetrics.toString());
                    performanceMetrics.getDoWorkoutExerciseSets().forEach(
                            doWorkoutExerciseSet -> {
                                logger.info("performance metrics set: " + doWorkoutExerciseSet.toString());
                            }
                    );
                }
        );
        return performanceMetricsList.stream()
                .map(doWorkoutPerformanceMetricsMapper::toDto)
                .toList();
    }

    @Override
    public List<DoWorkoutPerformanceMetricsDto> getAllDoWorkoutPerformanceMetrics(Date start, Date end) {
        List<DoWorkoutPerformanceMetrics> performanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics(start, end);

        return performanceMetricsList.stream()
                .map(doWorkoutPerformanceMetricsMapper::toDto)
                .toList();
    }

    @Override
    public void deleteDoWorkoutPerformanceMetrics(Long id) {
        doWorkoutPerformanceMetricsRepository.deleteById(id);
    }

    //TODO: refactor...
    @Override
    public DoWorkoutPerformanceMetricsDto updateDoWorkoutPerformanceMetrics(DoWorkoutPerformanceMetricsDto performanceMetricsDto) {
       return saveDoWorkoutPerformanceMetrics(performanceMetricsDto);
    }
}
