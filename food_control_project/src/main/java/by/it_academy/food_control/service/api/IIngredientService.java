package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Ingredient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IIngredientService {

    Ingredient getIngredientById(Long id_ingredient);

    void saveIngredient(Ingredient new_ingredient);

    void deleteIngredientById(Long id_ingredient);

    Page<Ingredient> getAllIngredient(PagesDTO pagesDTO);

    void updateIngredient(Ingredient ingredient_update, Long id);
}
