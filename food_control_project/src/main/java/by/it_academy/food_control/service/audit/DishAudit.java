package by.it_academy.food_control.service.audit;

import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.model.Dish;
import by.it_academy.food_control.model.api.entity_type.EEntity_type;
import by.it_academy.food_control.security.UserHolder;
import by.it_academy.food_control.service.api.IAuditService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class DishAudit {

    private final IAuditService auditService;
    private final UserHolder userHolder;


    public DishAudit(IAuditService auditService, UserHolder userHolder) {

        this.auditService = auditService;
        this.userHolder = userHolder;

    }

    @After("execution(* by.it_academy.food_control.service.DishService.saveDish(..))")
    public void createDishLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Dish dish = (Dish) args[0];
            Audit audit = new Audit();

            audit.setDate_event(dish.getData_update());
            audit.setLog("Создание нового блюда " + dish.getName_dish());

            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.DISH);
            audit.setEntity_id(dish.getId());

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при создании блюда");
        }
    }

    @After("execution(* by.it_academy.food_control.service.DishService.updateDish(..))")
    public void updateProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Dish dish = (Dish) args[0];
            Long dishId = (Long) args[1];
            Audit audit = new Audit();

            audit.setDate_event(dish.getData_update());
            audit.setLog("Обновление блюда " + dish.getName_dish());
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.DISH);
            audit.setEntity_id(dishId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при обновлении блюда");
        }
    }

    @After("execution(* by.it_academy.food_control.service.DishService.deleteDishById(..))")
    public void deleteProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Long dishId = (Long) args[0];
            Audit audit = new Audit();

            audit.setDate_event(LocalDateTime.now());
            audit.setLog("Удаление блюда " + dishId);
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.DISH);
            audit.setEntity_id(dishId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при удалении блюда");
        }
    }
}
