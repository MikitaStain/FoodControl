package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.FoodDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@RepositoryDefinition(domainClass = FoodDiary.class, idClass = Long.class)
public interface IFoodDiaryDao extends JpaRepository<FoodDiary, Long>{

    //List<FoodDiary> findByMeal_time_date(String date);
}
