package by.it_academy.food_control.service.api;

import by.it_academy.food_control.model.Profile;

import java.util.List;

public interface IProfileService {

    Profile getProfileById(Long id_profile);

    void saveProfile(Profile new_profile);

    void deleteProfileById(Long id_profile);

    List<Profile> getAllProfile();

    void updateProfile(Profile profile_update, Long id_profile);


}
