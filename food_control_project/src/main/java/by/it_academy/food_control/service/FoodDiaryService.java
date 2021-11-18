package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IFoodDiaryDao;
import by.it_academy.food_control.model.FoodDiary;
import by.it_academy.food_control.service.api.IFoodDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodDiaryService implements IFoodDiaryService {

    private final IFoodDiaryDao foodDiaryDao;

    @Autowired
    public FoodDiaryService(IFoodDiaryDao foodDiaryDao) {
        this.foodDiaryDao = foodDiaryDao;
    }

    @Override
    public FoodDiary getFoodDiaryById(Long id_FoodDiary) {

        return foodDiaryDao.findById(id_FoodDiary).orElseThrow(()->
                new IllegalArgumentException("Дневник не найден"));
    }

    @Override
    public void saveFoodDiary(FoodDiary new_foodDiary) {

        this.foodDiaryDao.save(new_foodDiary);
    }

    @Override
    public void deleteFoodDiaryById(Long id_foodDiary) {

        this.foodDiaryDao.deleteById(id_foodDiary);
    }

    @Override
    public List<FoodDiary> getAllFoodDiary() {

        return foodDiaryDao.findAll();
    }

    @Override
    public void updateFoodDiary(FoodDiary foodDiary_update, Long id_foodDiary) {

        FoodDiary foodDiary = getFoodDiaryById(id_foodDiary);

        foodDiary.setProduct(foodDiary_update.getProduct());
        foodDiary.setWeight(foodDiary_update.getWeight());
        foodDiary.setData_create(foodDiary_update.getData_create());
        foodDiary.setDish(foodDiary_update.getDish());
        foodDiary.setMeal_time_date(foodDiary_update.getMeal_time_date());
        foodDiary.setMeal_time_type(foodDiary_update.getMeal_time_type());
        foodDiary.setData_update(foodDiary_update.getData_update());

    }

    @Override
    public List<FoodDiary> getFoodDiaryForDay(String date) {

        return null;//this.foodDiaryDao.findByMeal_time_date(date);
    }
}
