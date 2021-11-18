package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishDAO extends JpaRepository<Dish, Long> {
}
