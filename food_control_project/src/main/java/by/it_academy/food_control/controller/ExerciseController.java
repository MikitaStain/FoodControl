package by.it_academy.food_control.controller;

import by.it_academy.food_control.model.Exercise;
import by.it_academy.food_control.service.api.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/food_diary/exercises")
public class ExerciseController {

    private final IExerciseService exerciseService;

    @Autowired
    public ExerciseController(IExerciseService exerciseService) {

        this.exerciseService = exerciseService;
    }


    //Получение списка упражнений
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllExercises() {

        List<Exercise> allExercise = this.exerciseService.getAllExercise();

        if (allExercise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allExercise, HttpStatus.OK);
    }


    //Получение карточки упражнений
    @RequestMapping(value = "/{id_exercise}", method = RequestMethod.GET)
    public ResponseEntity<?> getExercise(@PathVariable("id_exercise") Long id_exercise) {

        if (id_exercise == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Exercise exercise = this.exerciseService.getExerciseById(id_exercise);

            return new ResponseEntity<>(exercise, HttpStatus.OK);

        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //Создание карточки упражнений
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Exercise> save_exercise(@RequestBody @Valid Exercise exercise) {

        this.exerciseService.saveExercise(exercise);

        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }


    //Обновление карочки упражнений
    @RequestMapping(value = "/{id_exercise}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateExercise(@PathVariable("id_exercise") Long id_exercise,
                                            @PathVariable("dt_update") Long last_date_update,
                                            @RequestBody @Valid Exercise exercise) {

        if (id_exercise == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            this.exerciseService.updateExercise(exercise, id_exercise);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Удаление карточки упражнений
    @RequestMapping(value = "/{id_exercise}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExercise(@PathVariable("id_exercise") Long id_exercise,
                                            @PathVariable("dt_update") Long last_date_update) {

        if (id_exercise == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.exerciseService.getExerciseById(id_exercise);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        this.exerciseService.deleteExerciseById(id_exercise);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
