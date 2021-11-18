package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.WeightControl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeightControlDAO extends JpaRepository<WeightControl, Long> {
}
