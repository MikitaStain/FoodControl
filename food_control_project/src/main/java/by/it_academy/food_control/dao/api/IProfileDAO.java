package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileDAO extends JpaRepository<Profile, Long> {
}
