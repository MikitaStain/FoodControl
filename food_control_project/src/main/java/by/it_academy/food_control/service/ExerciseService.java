package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IExerciseDAO;
import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Exercise;
import by.it_academy.food_control.service.api.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseService implements IExerciseService {

    private final IExerciseDAO exerciseDAO;

    @Autowired
    public ExerciseService(IExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public Exercise getExerciseById(Long id_exercise) {

        return exerciseDAO.findById(id_exercise).orElseThrow(() ->
                new IllegalArgumentException("Не найден журнал тренировок"));
    }

    @Override
    public void saveExercise(Exercise new_exercise) {

        this.exerciseDAO.save(new_exercise);
    }

    @Override
    public void deleteExerciseById(Long id_exercise) {

        getExerciseById(id_exercise);
        this.exerciseDAO.deleteById(id_exercise);
    }

    @Override
    public Page<Exercise> getAllExercise(PagesDTO pagesDTO) {

        Pageable pageable = PageRequest.of(pagesDTO.getPageNumber(), pagesDTO.getPageSize());

        return exerciseDAO.findAll(pageable);
    }

    @Override
    public void updateExercise(Exercise exercise_update, Long id_exercise) {

        Exercise exercise = getExerciseById(id_exercise);

        exercise.setExercise_name(exercise_update.getExercise_name());
        exercise.setCalories(exercise_update.getCalories());
        exercise.setTraining_date(exercise_update.getTraining_date());
        exercise.setData_update(LocalDateTime.now());

    }
}
