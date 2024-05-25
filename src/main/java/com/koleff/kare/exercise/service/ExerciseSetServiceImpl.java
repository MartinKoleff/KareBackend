package com.koleff.kare.exercise.service;

import com.koleff.kare.exercise.mapper.ExerciseSetMapper;
import com.koleff.kare.exercise.models.dto.ExerciseSetDto;
import com.koleff.kare.exercise.models.entity.ExerciseSet;
import com.koleff.kare.exercise.repository.ExerciseSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ExerciseSetServiceImpl implements ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseSetMapper exerciseSetMapper;

    @Autowired
    public ExerciseSetServiceImpl(
            ExerciseSetRepository exerciseSetRepository,
            ExerciseSetMapper exerciseSetMapper
    ) {
        this.exerciseSetRepository = exerciseSetRepository;
        this.exerciseSetMapper = exerciseSetMapper;
    }

    @Override
    public ExerciseSetDto getSetById(UUID setId) {
        return exerciseSetRepository.findByExerciseSetId(setId)
                .map(exerciseSetMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException(""));
    }

    @Override
    public ExerciseSetDto saveExerciseSet(ExerciseSetDto exerciseSet) {
        ExerciseSet dbEntry = exerciseSetRepository.save(
                exerciseSetMapper.toEntity(exerciseSet)
        );

        return exerciseSetMapper.toDto(dbEntry);
    }

    @Override
    public List<ExerciseSetDto> saveAllExerciseSets(List<ExerciseSetDto> exerciseSets) {
        List<ExerciseSet> dbEntries = exerciseSetRepository.saveAll(
                exerciseSets.stream()
                        .map(exerciseSetMapper::toEntity)
                        .toList()
        );

        return dbEntries.stream()
                .map(exerciseSetMapper::toDto)
                .toList();
    }


    @Override
    public void updateExerciseSet(ExerciseSetDto exerciseSet) {
        exerciseSetRepository.save(
                exerciseSetMapper.toEntity(exerciseSet)
        );
    }


    @Override
    public void deleteExerciseSet(ExerciseSetDto exerciseSet) {
        exerciseSetRepository.delete(
                exerciseSetMapper.toEntity(exerciseSet)
        );
    }


    @Override
    public void deleteSetById(UUID setId) {
        exerciseSetRepository.deleteByExerciseSetId(setId);
    }
}