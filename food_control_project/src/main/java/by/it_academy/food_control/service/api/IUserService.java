package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.LoginDto;
import by.it_academy.food_control.model.User;

import java.util.List;

public interface IUserService {

    User getUserById(Long id_user);

    void saveUser(User new_user);

    void deleteUserById(Long id_user);

    List<User> getAllUsers();

    void updateUser(User user_update, Long id_user);

    boolean checkInLogin(String login);

    boolean chekInLoginAndPassword(LoginDto loginDto);

    User getUserByLogin(String login);
}
