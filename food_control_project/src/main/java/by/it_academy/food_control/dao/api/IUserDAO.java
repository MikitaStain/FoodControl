package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}
