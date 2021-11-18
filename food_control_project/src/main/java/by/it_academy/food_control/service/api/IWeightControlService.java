package by.it_academy.food_control.service.api;

import by.it_academy.food_control.model.WeightControl;

import java.util.List;

public interface IWeightControlService {

    WeightControl getWeightControlById(Long id_weightControl);

    void saveWeightControl(WeightControl new_weightControl);

    void deleteWeightControlById(Long id_weightControl);

    List<WeightControl> getAllWeightControl();

    void updateWeightControl(WeightControl WeightControl_update, Long id_weightControl);
}
