package com.koleff.kare.statistics.general.service;

import com.koleff.kare.common.Constants;
import com.koleff.kare.do_workout_performance_metrics.mapper.DoWorkoutPerformanceMetricsMapper;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutExerciseSet;
import com.koleff.kare.do_workout_performance_metrics.models.entity.DoWorkoutPerformanceMetrics;
import com.koleff.kare.do_workout_performance_metrics.repository.DoWorkoutExerciseSetRepository;
import com.koleff.kare.do_workout_performance_metrics.repository.DoWorkoutPerformanceMetricsRepository;
import com.koleff.kare.exercise.mapper.ExerciseMapper;
import com.koleff.kare.exercise.models.dto.ExerciseDto;
import com.koleff.kare.exercise.models.entity.Exercise;
import com.koleff.kare.exercise.models.entity.MuscleGroup;
import com.koleff.kare.exercise.repository.ExerciseRepository;
import com.koleff.kare.statistics.general.models.dto.MuscleGroupMaxWeight;
import com.koleff.kare.statistics.general.models.dto.WorkoutTotalTimesCompleted;
import com.koleff.kare.statistics.general.repository.GeneralStatisticsRepository;
import com.koleff.kare.workout.mapper.WorkoutMapper;
import com.koleff.kare.workout.models.dto.WorkoutDto;
import com.koleff.kare.workout.models.entity.Workout;
import com.koleff.kare.workout.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GeneralStatisticsServiceImpl implements GeneralStatisticsService {

    private final GeneralStatisticsRepository generalStatisticsRepository;
    private final DoWorkoutPerformanceMetricsRepository doWorkoutPerformanceMetricsRepository;
    private final DoWorkoutExerciseSetRepository doWorkoutExerciseSetRepository;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutMapper workoutMapper;
    private final ExerciseMapper exerciseMapper;

    @Autowired
    public GeneralStatisticsServiceImpl(GeneralStatisticsRepository generalStatisticsRepository,
                                        DoWorkoutPerformanceMetricsRepository doWorkoutPerformanceMetricsRepository,
                                        DoWorkoutExerciseSetRepository doWorkoutExerciseSetRepository,
                                        WorkoutRepository workoutRepository,
                                        ExerciseRepository exerciseRepository,
                                        WorkoutMapper workoutMapper,
                                        ExerciseMapper exerciseMapper) {
        this.generalStatisticsRepository = generalStatisticsRepository;
        this.doWorkoutPerformanceMetricsRepository = doWorkoutPerformanceMetricsRepository;
        this.doWorkoutExerciseSetRepository = doWorkoutExerciseSetRepository;
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutMapper = workoutMapper;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public WorkoutTotalTimesCompleted getWorkoutsCompleted() {
        Integer totalTimesCompleted = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics().size();
        List<Date> datesOfCompletion = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics()
                .stream()
                .map(DoWorkoutPerformanceMetrics::getDate)
                .toList();

        return new WorkoutTotalTimesCompleted(
                totalTimesCompleted,
                datesOfCompletion
        );
    }

    @Override
    public WorkoutTotalTimesCompleted getDistinctWorkoutsCompleted() {
        List<DoWorkoutPerformanceMetrics> doWorkoutPerformanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics()
                .stream()
                .toList();

        List<DoWorkoutPerformanceMetrics> distinctPerformanceMetricsList = distinctBy(
                doWorkoutPerformanceMetricsList,
                DoWorkoutPerformanceMetrics::getWorkoutId
        );
        Integer totalTimesCompleted = distinctPerformanceMetricsList.size();

        List<Date> datesOfCompletion = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics()
                .stream()
                .map(DoWorkoutPerformanceMetrics::getDate)
                .toList();

        return new WorkoutTotalTimesCompleted(
                totalTimesCompleted,
                datesOfCompletion
        );
    }

    @Override
    public Integer getWorkoutStreak() {
        List<DoWorkoutPerformanceMetrics> workoutPerformanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics()
                .stream()
                .toList();

        List<Date> dates = workoutPerformanceMetricsList.stream()
                .map(DoWorkoutPerformanceMetrics::getDate)
                .sorted(Comparator.reverseOrder())
                .toList();

        int streak = 1;

        for (int i = 1; i < dates.size(); i++) {
            if (isConsecutiveDay(dates.get(i - 1), dates.get(i))) {
                streak++;
            } else {
                break;
            }
        }

        return streak;
    }

    private boolean isConsecutiveDay(Date date1, Date date2) {
        long diffMillis = date1.getTime() - date2.getTime();
        long diffDays = diffMillis / (1000 * 60 * 60 * 24);
        return diffDays == 1L;
    }

    public static <T> List<T> distinctBy(List<T> list, Function<? super T, ?> keyExtractor) {
        Set<Object> seen = new HashSet<>();
        return list.stream()
                .filter(element -> seen.add(keyExtractor.apply(element)))
                .toList();
    }

    @Override
    public WorkoutDto getMostFrequentWorkout() {
        List<DoWorkoutPerformanceMetrics> doWorkoutPerformanceMetricsList = doWorkoutPerformanceMetricsRepository.findAllWorkoutPerformanceMetrics()
                .stream()
                .toList();

        List<DoWorkoutPerformanceMetrics> distinctPerformanceMetricsList = distinctBy(
                doWorkoutPerformanceMetricsList,
                DoWorkoutPerformanceMetrics::getWorkoutId
        );

        List<WorkoutDto> workouts = distinctPerformanceMetricsList.stream()
                .map(doWorkoutPerformanceMetrics ->
                        workoutRepository.getWorkoutByWorkoutId(doWorkoutPerformanceMetrics.getWorkoutId())
                )
                .map(workoutMapper::toDto)
                .toList();

        //Count frequencies
        Map<WorkoutDto, Long> workoutFrequency = workouts.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        //Get most frequent workout
        WorkoutDto defaultWorkout = workoutMapper.toDto(new Workout());
        WorkoutDto mostFrequentWorkout = workoutFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(defaultWorkout);

        return mostFrequentWorkout;
    }


    @Override
    public MuscleGroup getMostTrainedMuscleGroup() {
        List<DoWorkoutExerciseSet> doWorkoutExerciseSets = doWorkoutExerciseSetRepository.findAll();

        List<ExerciseDto> catalogExercises = exerciseRepository.findByWorkoutId(Constants.CATALOG_WORKOUT_ID)
                .stream()
                .map(exerciseMapper::toDto)
                .toList();

        // Map exerciseId to muscleGroup
        Map<Long, MuscleGroup> exerciseIdToMuscleGroup = catalogExercises.stream()
                .collect(Collectors.toMap(
                        ExerciseDto::id,
                        exercise -> MuscleGroup.fromId(exercise.muscleGroupId())
                ));

        // Count how many times each exerciseId appears
        Map<Long, Long> exerciseCount = doWorkoutExerciseSets.stream()
                .collect(Collectors.groupingBy(DoWorkoutExerciseSet::getExerciseId, Collectors.counting()));

        // Get the most frequent exerciseId
        Optional<Long> mostTrainedExerciseId = exerciseCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        // Find the corresponding muscle group
        return mostTrainedExerciseId
                .map(exerciseIdToMuscleGroup::get)
                .orElse(MuscleGroup.NONE);
    }

    @Override
    public ExerciseDto getMostTrainedExercise() {
        List<DoWorkoutExerciseSet> allExerciseSets = doWorkoutExerciseSetRepository.findAll();

        List<ExerciseDto> catalogExercises = exerciseRepository.findByWorkoutId(Constants.CATALOG_WORKOUT_ID)
                .stream()
                .map(exerciseMapper::toDto)
                .toList();

        //Count how many times each exerciseId appears
        Map<Long, Integer> exerciseIdCount = new HashMap<>();
        for (DoWorkoutExerciseSet exerciseSet : allExerciseSets) {
            long exerciseId = exerciseSet.getExerciseId();
            exerciseIdCount.put(exerciseId, exerciseIdCount.getOrDefault(exerciseId, 0) + 1);
        }

        Optional<Map.Entry<Long, Integer>> mostTrainedEntry = exerciseIdCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        ExerciseDto defaultExercise = exerciseMapper.toDto(new Exercise());
        if (mostTrainedEntry.isEmpty()) {
            return defaultExercise;
        }

        long mostTrainedExerciseId = mostTrainedEntry.get().getKey();

        //Find the matching catalog exercise
        Optional<ExerciseDto> mostTrainedExercise = catalogExercises.stream()
                .filter(exercise -> exercise.id() == mostTrainedExerciseId)
                .findFirst();

        return mostTrainedExercise.orElse(defaultExercise);
    }


    @Override
    public Float getTotalWeightLifted() {
        return generalStatisticsRepository.getTotalWeightLifted();
    }

    @Override
    public MuscleGroupMaxWeight getStrongestMuscleGroup() {
        List<DoWorkoutExerciseSet> allExerciseSets = doWorkoutExerciseSetRepository.findAll();
        List<ExerciseDto> catalogExercises = exerciseRepository.findByWorkoutId(Constants.CATALOG_WORKOUT_ID)
                .stream()
                .map(exerciseMapper::toDto)
                .toList();

        Map<Long, MuscleGroup> exerciseIdToMuscleGroup = catalogExercises.stream()
                .collect(Collectors.toMap(
                        ExerciseDto::id,
                        exercise -> MuscleGroup.fromId(exercise.muscleGroupId())
                ));

        Map<MuscleGroup, Float> maxWeightLifted = new HashMap<>();

        for (DoWorkoutExerciseSet set : allExerciseSets) {
            MuscleGroup muscleGroup = exerciseIdToMuscleGroup.get(set.getExerciseId());
            if (muscleGroup == null) continue;

            float weight = set.getWeight() != null ? set.getWeight() : 0f;
            maxWeightLifted.put(
                    muscleGroup,
                    Math.max(maxWeightLifted.getOrDefault(muscleGroup, 0f), weight)
            );
        }

        Optional<Map.Entry<MuscleGroup, Float>> strongest = maxWeightLifted.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        MuscleGroup strongestMuscleGroup = strongest.map(Map.Entry::getKey).orElse(MuscleGroup.NONE);
        float maxWeight = strongest.map(Map.Entry::getValue).orElse(0f);

        return new MuscleGroupMaxWeight(strongestMuscleGroup, maxWeight);
    }


    @Override
    public MuscleGroupMaxWeight getMuscleGroupTotalWeightLifted(Integer muscleGroupId) {
        List<DoWorkoutExerciseSet> allExerciseSets = doWorkoutExerciseSetRepository.findAll();
        List<ExerciseDto> catalogExercises = exerciseRepository.findByWorkoutId(Constants.CATALOG_WORKOUT_ID)
                .stream()
                .map(exerciseMapper::toDto)
                .toList();

        Map<Long, MuscleGroup> exerciseIdToMuscleGroup = catalogExercises.stream()
                .collect(Collectors.toMap(
                        ExerciseDto::id,
                        exercise -> MuscleGroup.fromId(exercise.muscleGroupId())
                ));

        Map<MuscleGroup, Float> totalWeightLifted = new HashMap<>();

        for (DoWorkoutExerciseSet set : allExerciseSets) {
            MuscleGroup muscleGroup = exerciseIdToMuscleGroup.get(set.getExerciseId());
            if (muscleGroup == null) continue;

            float weight = set.getWeight() != null ? set.getWeight() : 0f;
            float weightLifted = weight * set.getReps();

            totalWeightLifted.put(
                    muscleGroup,
                    totalWeightLifted.getOrDefault(muscleGroup, 0f) + weightLifted
            );
        }

        MuscleGroup selectedMuscleGroup = MuscleGroup.fromId(muscleGroupId);
        float maxWeight = totalWeightLifted.getOrDefault(selectedMuscleGroup, 0f);
        return new MuscleGroupMaxWeight(selectedMuscleGroup, maxWeight);
    }
}