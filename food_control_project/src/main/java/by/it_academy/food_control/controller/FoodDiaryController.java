package by.it_academy.food_control.controller;

import by.it_academy.food_control.model.FoodDiary;
import by.it_academy.food_control.service.api.IFoodDiaryService;
import by.it_academy.food_control.service.api.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/diary/food")
public class FoodDiaryController {

    private final IFoodDiaryService foodDiaryService;
    private final IProfileService profileService;

    @Autowired
    public FoodDiaryController(IFoodDiaryService foodDiaryService, IProfileService profileService) {

        this.foodDiaryService = foodDiaryService;
        this.profileService = profileService;
    }


    //Получение списка приемов пищи
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEating(@PathVariable("id_profile") Long id_profile) {

        List<FoodDiary> allEating = this.foodDiaryService.getAllFoodDiary();

        if (allEating.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allEating, HttpStatus.OK);
    }


    //Получение списка приемов пищи за день
    @RequestMapping(value = "/byDay/{day}", method = RequestMethod.GET)
    public ResponseEntity<?> getEating(@RequestBody @PathVariable("id_profile") Long id_profile,
                                       @RequestBody @PathVariable("day") String date) {

        if (id_profile == null || date == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.profileService.getProfileById(id_profile);
            List<FoodDiary> foodDiaryForDay = foodDiaryService.getAllFoodDiary();//getFoodDiaryForDay(date);

            if (foodDiaryForDay.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(foodDiaryForDay, HttpStatus.OK);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //Создание приема пищи
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<FoodDiary> save_new_food_diary(@RequestBody @Valid FoodDiary foodDiary) {

        this.foodDiaryService.saveFoodDiary(foodDiary);

        return new ResponseEntity<>(foodDiary, HttpStatus.OK);
    }


    //Обновление приема пищи
    @RequestMapping(value = "/{id_food}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateFoodDiary(@PathVariable("id_profile") Long id_profile,
                                             @PathVariable("id_food") Long id_food,
                                             @PathVariable("dt_update") Long last_date_update,
                                             @RequestBody @Valid FoodDiary foodDiary) {

        if (id_profile == null
                || id_food == null
                || last_date_update == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.profileService.getProfileById(id_profile);
            this.foodDiaryService.getFoodDiaryById(id_food);
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        this.foodDiaryService.updateFoodDiary(foodDiary, id_food);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Удаление приема пищи
    @RequestMapping(value = "/{id_food}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFoodDiary(@PathVariable("id_profile") Long id_profile,
                                             @PathVariable("id_food") Long id_food,
                                             @PathVariable("dt_update") Long last_date_update) {

        if (id_food == null || id_profile == null || last_date_update == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.profileService.getProfileById(id_profile);
            this.foodDiaryService.getFoodDiaryById(id_food);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        this.foodDiaryService.deleteFoodDiaryById(id_food);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id_food}")
    public ResponseEntity<?> getEating(@PathVariable("id_profile") Long id_profile,
                                       @PathVariable("id_food") Long id_food) {

        if (id_profile == null || id_food == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.profileService.getProfileById(id_profile);
            FoodDiary foodDiaryById = this.foodDiaryService.getFoodDiaryById(id_food);

            return new ResponseEntity<>(foodDiaryById, HttpStatus.OK);
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
