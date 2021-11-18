package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExerciseDAO extends JpaRepository<Exercise, Long> {
}
