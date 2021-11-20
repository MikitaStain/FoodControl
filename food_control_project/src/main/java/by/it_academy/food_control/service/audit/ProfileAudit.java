package by.it_academy.food_control.service.audit;

import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.model.Product;
import by.it_academy.food_control.model.Profile;
import by.it_academy.food_control.model.User;
import by.it_academy.food_control.model.api.entity_type.EEntity_type;
import by.it_academy.food_control.security.UserHolder;
import by.it_academy.food_control.service.api.IAuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

import java.time.LocalDateTime;

public class ProfileAudit {


    private final IAuditService auditService;
    private final UserHolder userHolder;


    public ProfileAudit(IAuditService auditService, UserHolder userHolder) {

        this.auditService = auditService;
        this.userHolder = userHolder;

    }

    @After("execution(* by.it_academy.food_control.service.ProfileService.saveProfile(..))")
    public void createProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Profile profile = (Profile) args[0];
            Audit audit = new Audit();
            User user = userHolder.getUser();

            audit.setDate_event(profile.getDate_update());
            audit.setLog("Создание нового профиля для " + user.getLogin());

            audit.setUser(user);
            audit.setEntity_type(EEntity_type.PROFILE);
            audit.setEntity_id(profile.getId());

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при создании профиля");
        }
    }

    @After("execution(* by.it_academy.food_control.service.ProfileService.updateProfile(..))")
    public void updateProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Profile profile = (Profile) args[0];
            Long profileId = (Long) args[1];
            Audit audit = new Audit();
            User user = userHolder.getUser();

            audit.setDate_event(profile.getDate_update());
            audit.setLog("Обновление профиля " + user.getLogin());
            audit.setUser(user);
            audit.setEntity_type(EEntity_type.PROFILE);
            audit.setEntity_id(profileId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при обновлении профиля");
        }
    }
}
