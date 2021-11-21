package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Dish;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDishService {

    Dish getDishById(Long id_dish);

    void saveDish(Dish new_dish);

    void deleteDishById(Long id_dish);

    Page<Dish> getAllDish(PagesDTO pagesDTO);

    void updateDish(Dish dish_update, Long id);
}
