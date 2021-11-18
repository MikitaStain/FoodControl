package by.it_academy.food_control.service.api;

import by.it_academy.food_control.model.Dish;

import java.util.List;

public interface IDishService {

    Dish getDishById(Long id_dish);

    void saveDish(Dish new_dish);

    void deleteDishById(Long id_dish);

    List<Dish> getAllDish();

    void updateDish(Dish dish_update, Long id);
}
