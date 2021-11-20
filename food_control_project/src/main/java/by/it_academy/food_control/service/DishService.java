package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IDishDAO;
import by.it_academy.food_control.model.Dish;
import by.it_academy.food_control.service.api.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DishService implements IDishService {

    private final IDishDAO dishDAO;

    @Autowired
    public DishService(IDishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    @Override
    public Dish getDishById(Long id_dish) {
        return dishDAO.findById(id_dish).orElseThrow(()->new IllegalArgumentException("Блюдо не найдено"));
    }

    @Override
    public void saveDish(Dish new_dish) {
        this.dishDAO.save(new_dish);
    }

    @Override
    public void deleteDishById(Long id_dish) {
        this.dishDAO.deleteById(id_dish);
    }

    @Override
    public List<Dish> getAllDish() {

        List<Dish> all_dishes = this.dishDAO.findAll();

        return all_dishes;
    }

    @Override
    public void updateDish(Dish dish_update, Long id) {

        Dish dish = getDishById(id);

        dish.setName_dish(dish_update.getName_dish());
        dish.setIngredients(dish_update.getIngredients());
        dish.setData_created(LocalDateTime.now());
        dish.setUser(dish_update.getUser());

        this.dishDAO.saveAndFlush(dish);
    }
}
