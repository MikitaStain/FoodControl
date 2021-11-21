package by.it_academy.food_control.controller;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.WeightControl;
import by.it_academy.food_control.service.api.IWeightControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/food_diary/weight_control")
public class WeightControlController {

    private final IWeightControlService weightControlService;

    @Autowired
    public WeightControlController(IWeightControlService weightControlService) {

        this.weightControlService = weightControlService;
    }


    //Получение списка взвешиваний
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllWeightControl(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        PagesDTO pagesDTO = new PagesDTO();
        pagesDTO.setPageNumber(pageNumber);
        pagesDTO.setPageSize(pageSize);

        Page<WeightControl> page = this.weightControlService.getAllWeightControl(pagesDTO);
        List<WeightControl> allWeightControl = page.getContent();

        if (allWeightControl.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allWeightControl, HttpStatus.OK);
    }


    //Получение карточки взвешивания
    @RequestMapping(value = "/{id_weight_control}", method = RequestMethod.GET)
    public ResponseEntity<?> getWeightControl(@PathVariable("id_weight_control") Long id) {

        if (id == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            WeightControl weightControl = this.weightControlService.getWeightControlById(id);

            return new ResponseEntity<>(weightControl, HttpStatus.OK);

        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //Создание карточки взвешивания
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<WeightControl> save_new_weight(@RequestBody @Valid WeightControl weightControl) {

        this.weightControlService.saveWeightControl(weightControl);

        return new ResponseEntity<>(weightControl, HttpStatus.OK);
    }


    //Обновление карочки взвешивания
    @RequestMapping(value = "/{id_weight_control}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateWeightControl(@PathVariable("id_weight_control") Long id_weight,
                                                 @PathVariable("dt_update") Long last_date_update,
                                                 @RequestBody @Valid WeightControl weightControl) {

        if (id_weight == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            this.weightControlService.updateWeightControl(weightControl, id_weight);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //Удаление карточки взвешивания
    @RequestMapping(value = "/{id_weight_control}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWeightControl(@PathVariable("id_weight_control") Long id_weight,
                                                 @PathVariable("dt_update") Long last_date_update) {

        if (id_weight == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.weightControlService.deleteWeightControlById(id_weight);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
