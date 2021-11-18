package by.it_academy.food_control.controller;

import by.it_academy.food_control.model.Profile;
import by.it_academy.food_control.service.api.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/profile")
public class ProfileController {
//TODO передалть весь контролер
    private final IProfileService profileService;

    @Autowired
    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Profile> getProfile(@PathVariable("id") Long id_profile) {

        if (id_profile == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Profile profile = this.profileService.getProfileById(id_profile);

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    //TODO валидация
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Profile> save_new_profile(@RequestBody @Valid Profile profile) {

        this.profileService.saveProfile(profile);

        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    //TODO оптимистическая блокировка
    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<Profile> deleteProfileById(@PathVariable("id") Long id_profile,
                                                     @PathVariable("dt_update") LocalDateTime last_date_update) {

        Profile profile = profileService.getProfileById(id_profile);

        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.profileService.deleteProfileById(id_profile);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //TODO пагинация
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Profile>> getAllProfile() {

        List<Profile> profiles = this.profileService.getAllProfile();

        if (profiles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    //TODO подумать еще
    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<Profile> updateProfile(@PathVariable("id") Long id_profile,
                                                 @PathVariable("dt_update") LocalDateTime last_date_update,
                                                 @RequestBody @Valid Profile profile) {

        if (id_profile == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.profileService.updateProfile(profile, id_profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
