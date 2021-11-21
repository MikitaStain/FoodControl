package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Exercise;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IExerciseService {

    Exercise getExerciseById(Long id_exercise);

    void saveExercise(Exercise new_exercise);

    void deleteExerciseById(Long id_exercise);

    Page<Exercise> getAllExercise(PagesDTO pagesDTO);

    void updateExercise(Exercise exercise_update, Long id_exercise);
}
