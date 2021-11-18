package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IWeightControlDAO;
import by.it_academy.food_control.model.WeightControl;
import by.it_academy.food_control.service.api.IWeightControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeightService implements IWeightControlService {

    private final IWeightControlDAO weightControlDAO;

    @Autowired
    public WeightService(IWeightControlDAO weightControlDAO) {
        this.weightControlDAO = weightControlDAO;
    }

    @Override
    public WeightControl getWeightControlById(Long id_weightControl) {

        return weightControlDAO.findById(id_weightControl).orElseThrow(() ->
                new IllegalArgumentException("Не найден дневник веса"));
    }

    @Override
    public void saveWeightControl(WeightControl new_weightControl) {

        this.weightControlDAO.save(new_weightControl);
    }

    @Override
    public void deleteWeightControlById(Long id_weightControl) {

        this.weightControlDAO.deleteById(id_weightControl);
    }

    @Override
    public List<WeightControl> getAllWeightControl() {
        return weightControlDAO.findAll();
    }

    @Override
    public void updateWeightControl(WeightControl weightControl_update, Long id_weightControl) {

        WeightControl weightControl = getWeightControlById(id_weightControl);

        weightControl.setNew_weight(weightControl_update.getNew_weight());
        weightControl.setWeigh_in_date(weightControl_update.getWeigh_in_date());
        weightControl.setDate_update(LocalDateTime.now());

        this.weightControlDAO.saveAndFlush(weightControl);
    }
}
