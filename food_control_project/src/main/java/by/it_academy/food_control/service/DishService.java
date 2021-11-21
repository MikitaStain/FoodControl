package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IDishDAO;
import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Dish;
import by.it_academy.food_control.service.api.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return dishDAO.findById(id_dish).orElseThrow(
                ()->new IllegalArgumentException("Блюдо не найдено"));
    }

    @Override
    public void saveDish(Dish new_dish) {
        this.dishDAO.save(new_dish);
    }

    @Override
    public void deleteDishById(Long id_dish) {

        getDishById(id_dish);

        this.dishDAO.deleteById(id_dish);
    }

    @Override
    public Page<Dish> getAllDish(PagesDTO pagesDTO) {

        Pageable pageable = PageRequest.of(pagesDTO.getPageNumber(), pagesDTO.getPageSize());

        return this.dishDAO.findAll(pageable);
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
