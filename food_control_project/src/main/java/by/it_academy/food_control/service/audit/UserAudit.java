package by.it_academy.food_control.service.audit;

import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.model.User;
import by.it_academy.food_control.model.api.entity_type.EEntity_type;
import by.it_academy.food_control.security.UserHolder;
import by.it_academy.food_control.service.api.IAuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class UserAudit {

    private final IAuditService auditService;
    private final UserHolder userHolder;


    public UserAudit(IAuditService auditService, UserHolder userHolder) {

        this.auditService = auditService;
        this.userHolder = userHolder;

    }

    @After("execution(* by.it_academy.food_control.service.UserService.saveUser(..))")
    public void createdUserLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            User user = (User) args[0];
            Audit audit = new Audit();

            audit.setDate_event(user.getDate_update());
            audit.setLog("Регистрация пользователя " + user.getLogin());

            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.USER);
            audit.setEntity_id(user.getId());

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при создании пользователя");
        }
    }

    @After("execution(* by.it_academy.food_control.service.UserService.updateUser(..))")
    public void updatedUserLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            User user = (User) args[0];
            Audit audit = new Audit();

            audit.setDate_event(user.getDate_update());
            audit.setLog("Обновление пользователя " + user.getLogin());

            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.USER);
            audit.setEntity_id(user.getId());


            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при обновлении пользователя");
        }
    }
}
