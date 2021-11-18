package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIngredientDAO extends JpaRepository<Ingredient, Long> {
}
