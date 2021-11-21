package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.WeightControl;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IWeightControlService {

    WeightControl getWeightControlById(Long id_weightControl);

    void saveWeightControl(WeightControl new_weightControl);

    void deleteWeightControlById(Long id_weightControl);

    Page<WeightControl> getAllWeightControl(PagesDTO pagesDTO);

    void updateWeightControl(WeightControl WeightControl_update, Long id_weightControl);
}
