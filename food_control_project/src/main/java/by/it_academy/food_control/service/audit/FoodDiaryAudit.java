package by.it_academy.food_control.service.audit;

import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.model.FoodDiary;
import by.it_academy.food_control.model.Product;
import by.it_academy.food_control.model.User;
import by.it_academy.food_control.model.api.entity_type.EEntity_type;
import by.it_academy.food_control.security.UserHolder;
import by.it_academy.food_control.service.api.IAuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class FoodDiaryAudit {


    private final IAuditService auditService;
    private final UserHolder userHolder;


    public FoodDiaryAudit(IAuditService auditService, UserHolder userHolder) {

        this.auditService = auditService;
        this.userHolder = userHolder;

    }

    @After("execution(* by.it_academy.food_control.service.FoodDiaryService.saveFoodDiary(..))")
    public void createFoodDiaryLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            FoodDiary foodDiary = (FoodDiary) args[0];
            Audit audit = new Audit();
            User user = userHolder.getUser();

            audit.setDate_event(foodDiary.getData_update());
            audit.setLog("Создание нового дневника питания для пользователя" + user.getLogin());

            audit.setUser(user);
            audit.setEntity_type(EEntity_type.FOOD_DIARY);
            audit.setEntity_id(foodDiary.getId());

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при создании дневника питания");
        }
    }

    @After("execution(* by.it_academy.food_control.service.FoodDiaryService.updateFoodDiary(..))")
    public void updateFoodDiaryLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];
            Long foodDiaryId = (Long) args[1];
            Audit audit = new Audit();
            User user = userHolder.getUser();

            audit.setDate_event(product.getData_update());
            audit.setLog("Обновление дневника питания для пользователя" + user.getLogin());
            audit.setUser(user);
            audit.setEntity_type(EEntity_type.FOOD_DIARY);
            audit.setEntity_id(foodDiaryId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при обновлении дневника питания");
        }
    }

    @Before("execution(* by.it_academy.food_control.service.FoodDiaryService.deleteFoodDiaryById(..))")
    public void deleteFoodDiaryLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Long foodDiaryId = (Long) args[0];
            Audit audit = new Audit();

            audit.setDate_event(LocalDateTime.now());
            audit.setLog("Удаление дневника питания " + foodDiaryId);
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.FOOD_DIARY);
            audit.setEntity_id(foodDiaryId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при удалении дневника питания");
        }
    }
}
