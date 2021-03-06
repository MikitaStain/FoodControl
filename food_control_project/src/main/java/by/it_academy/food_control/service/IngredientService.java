package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IIngredientDAO;
import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Ingredient;
import by.it_academy.food_control.service.api.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService implements IIngredientService {

    private final IIngredientDAO ingredientDAO;

    @Autowired
    public IngredientService(IIngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }

    @Override
    public Ingredient getIngredientById(Long id_ingredient) {

        return ingredientDAO.findById(id_ingredient).orElseThrow(()->new IllegalArgumentException("Ингредиент не найден"));
    }

    @Override
    public void saveIngredient(Ingredient new_ingredient) {

        this.ingredientDAO.save(new_ingredient);
    }

    @Override
    public void deleteIngredientById(Long id_ingredient) {

        getIngredientById(id_ingredient);
        this.ingredientDAO.deleteById(id_ingredient);
    }

    @Override
    public Page<Ingredient> getAllIngredient(PagesDTO pagesDTO) {

        Pageable pageable = PageRequest.of(pagesDTO.getPageNumber(), pagesDTO.getPageSize());

        return ingredientDAO.findAll(pageable);
    }

    @Override
    public void updateIngredient(Ingredient ingredient_update, Long id_ingredient) {

        Ingredient ingredient = getIngredientById(id_ingredient);

        ingredient.setProduct(ingredient_update.getProduct());
        ingredient.setWeight(ingredient_update.getWeight());

        this.ingredientDAO.saveAndFlush(ingredient);
    }
}
