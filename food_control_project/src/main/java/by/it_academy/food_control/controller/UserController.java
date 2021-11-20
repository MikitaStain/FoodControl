package by.it_academy.food_control.controller;

import by.it_academy.food_control.dto.LoginDto;
import by.it_academy.food_control.dto.UserDto;
import by.it_academy.food_control.model.User;
import by.it_academy.food_control.security.UserHolder;
import by.it_academy.food_control.service.api.IUserService;
import by.it_academy.food_control.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    private IUserService userService;
    private JWTToken jwtToken;
    private UserHolder userHolder;


    @Autowired
    public UserController(IUserService userService, JWTToken jwtToken, UserHolder userHolder) {
        this.userService = userService;
        this.jwtToken = jwtToken;
        this.userHolder = userHolder;

    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> save_new_user(@RequestBody @Valid User user) {

        if (this.userService.isCheckInLogin(user.getLogin())) {

            this.userService.saveUser(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Логин занят", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        if (this.userService.isChekInLoginAndPassword(loginDto)) {

            String token = jwtToken.getJWTToken(loginDto.getLogin());
            UserDto userDto = new UserDto();
            userDto.setUser(this.userService.getUserByLogin(loginDto.getLogin()));
            userDto.setToken(token);
            return new ResponseEntity<>(token, HttpStatus.OK);

        }
        return new ResponseEntity<>("Логин или пароль не верны", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable(value = "name",required = false) String name) {

        if (name == null) {
            name = userHolder.getAuthentication().getName();
        }
        User userByLogin = this.userService.getUserByLogin(name);

        return new ResponseEntity<>(userByLogin,HttpStatus.OK);
    }

}
