package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IProfileDAO;
import by.it_academy.food_control.model.Profile;
import by.it_academy.food_control.service.api.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileService implements IProfileService {

    private final IProfileDAO profileDAO;

    @Autowired
    public ProfileService(IProfileDAO profileDAO) {

        this.profileDAO = profileDAO;
    }

    @Override
    public Profile getProfileById(Long id_profile) {

        return profileDAO.findById(id_profile).orElseThrow(()->new IllegalArgumentException("Профиль не найден"));
    }

    @Override
    public void saveProfile(Profile new_profile) {

        this.profileDAO.save(new_profile);
    }

    @Override
    public void deleteProfileById(Long id_profile) {

        this.profileDAO.deleteById(id_profile);
    }

    @Override
    public List<Profile> getAllProfile() {

        return profileDAO.findAll();
    }

    @Override
    public void updateProfile(Profile profile_update, Long id_profile) {

        Profile profile = getProfileById(id_profile);

        profile.setDate_of_birth(profile_update.getDate_of_birth());
        profile.setGender(profile_update.getGender());
        profile.setGoal(profile_update.getGoal());
        profile.setHeight(profile_update.getHeight());
        profile.setLifestyle(profile_update.getLifestyle());
        profile.setDate_update(LocalDateTime.now());

        this.profileDAO.saveAndFlush(profile);
    }
}
