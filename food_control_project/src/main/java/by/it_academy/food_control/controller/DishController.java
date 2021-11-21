package by.it_academy.food_control.controller;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Dish;
import by.it_academy.food_control.service.api.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class DishController {

    private final IDishService dishService;

    @Autowired
    public DishController(IDishService dishService) {
        this.dishService = dishService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDish(@PathVariable("id") Long dishes_id) {

        if (dishes_id == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Dish dish = this.dishService.getDishById(dishes_id);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Dish> save_new_dish(@RequestBody @Valid Dish dish) {

        this.dishService.saveDish(dish);

        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDishById(@PathVariable("id") Long id_dish,
                                            @PathVariable("dt_update") LocalDateTime last_date_update) {

        if (id_dish == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            this.dishService.deleteDishById(id_dish);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //TODO пагинация
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDish(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        PagesDTO pagesDTO = new PagesDTO();
        pagesDTO.setPageNumber(pageNumber);
        pagesDTO.setPageSize(pageSize);

        Page<Dish> page = this.dishService.getAllDish(pagesDTO);
        List<Dish> dishes = page.getContent();

        if (dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    //TODO подумать еще
    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id_dish,
                                           @PathVariable("dt_update") LocalDateTime last_date_update,
                                           @RequestBody @Valid Dish dish) {

        if (id_dish == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.dishService.updateDish(dish, id_dish);
        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
