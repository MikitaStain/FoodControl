package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.FoodDiary;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IFoodDiaryService {

    FoodDiary getFoodDiaryById(Long id_FoodDiary);

    void saveFoodDiary(FoodDiary new_foodDiary);

    void deleteFoodDiaryById(Long id_foodDiary);

    Page<FoodDiary> getAllFoodDiary(PagesDTO pagesDTO);

    void updateFoodDiary(FoodDiary foodDiary_update, Long id_foodDiary);

    List<FoodDiary> getFoodDiaryForDay(String date);
}
