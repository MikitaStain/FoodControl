package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Profile;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProfileService {

    Profile getProfileById(Long id_profile);

    void saveProfile(Profile new_profile);

    void deleteProfileById(Long id_profile);

    Page<Profile> getAllProfile(PagesDTO pagesDTO);

    void updateProfile(Profile profile_update, Long id_profile);


}
