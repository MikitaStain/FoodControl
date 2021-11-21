package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.LoginDto;
import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {

    User getUserById(Long id_user);

    void saveUser(User new_user);

    void deleteUserById(Long id_user);

    Page<User> getAllUsers(PagesDTO pagesDTO);

    void updateUser(User user_update, Long id_user);

    boolean isCheckInLogin(String login);

    boolean isChekInLoginAndPassword(LoginDto loginDto);

    User getUserByLogin(String login);
}
