package com.koleff.kare.do_workout_performance_metrics.services;

import com.koleff.kare.do_workout_performance_metrics.mapper.DoWorkoutExerciseSetMapper;
import com.koleff.kare.do_workout_performance_metrics.models.dto.DoWorkoutExerciseSetDto;
import com.koleff.kare.do_workout_performance_metrics.repository.DoWorkoutExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoWorkoutExerciseSetServiceImpl implements DoWorkoutExerciseSetService {

    private final DoWorkoutExerciseSetRepository doWorkoutExerciseSetRepository;
    private final DoWorkoutExerciseSetMapper doWorkoutExerciseSetMapper;

    @Autowired
    public DoWorkoutExerciseSetServiceImpl(
            DoWorkoutExerciseSetRepository doWorkoutExerciseSetRepository,
            DoWorkoutExerciseSetMapper doWorkoutExerciseSetMapper
    ) {
        this.doWorkoutExerciseSetRepository = doWorkoutExerciseSetRepository;
        this.doWorkoutExerciseSetMapper = doWorkoutExerciseSetMapper;
    }

    @Override
    public void saveDoWorkoutExerciseSet(DoWorkoutExerciseSetDto exerciseSet) {
        doWorkoutExerciseSetRepository.save(
                doWorkoutExerciseSetMapper.toEntity(exerciseSet)
        );
    }

    @Override
    public void saveAllDoWorkoutExerciseSet(List<DoWorkoutExerciseSetDto> exerciseSets) {
        doWorkoutExerciseSetRepository.saveAll(
                exerciseSets.stream()
                        .map(doWorkoutExerciseSetMapper::toEntity)
                        .toList()
        );
    }
}
