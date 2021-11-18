package by.it_academy.food_control.dto;

import by.it_academy.food_control.model.User;

public class UserDto {

    private User user;

    private String token;

    public UserDto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
