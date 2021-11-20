package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IUserDAO;
import by.it_academy.food_control.dto.LoginDto;
import by.it_academy.food_control.model.User;
import by.it_academy.food_control.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserDAO userDAO;

    @Autowired
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(Long id_user) {
        return userDAO.findById(id_user).orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
    }

    @Override
    public void saveUser(User new_user) {

        this.userDAO.save(new_user);
    }

    @Override
    public void deleteUserById(Long id_user) {
        this.userDAO.deleteById(id_user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void updateUser(User user_update, Long id_user) {

        User user = getUserById(id_user);

        user.setLogin(user_update.getLogin());
        user.setPassword(user_update.getPassword());
        user.setDate_update(LocalDateTime.now());

        this.userDAO.saveAndFlush(user);
    }

    @Override
    public boolean isCheckInLogin(String login) {

        return this.userDAO.findByLogin(login) == null;
    }

    @Override
    public boolean isChekInLoginAndPassword(LoginDto loginDto) {

        User user = this.userDAO.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
        return user != null;
    }

    @Override
    public User getUserByLogin(String login) {

        return this.userDAO.findByLogin(login);
    }
}
