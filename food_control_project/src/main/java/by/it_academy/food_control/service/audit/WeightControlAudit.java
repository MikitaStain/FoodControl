package by.it_academy.food_control.service.audit;

import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.model.WeightControl;
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
public class WeightControlAudit {


    private final IAuditService auditService;
    private final UserHolder userHolder;


    public WeightControlAudit(IAuditService auditService, UserHolder userHolder) {

        this.auditService = auditService;
        this.userHolder = userHolder;

    }

    @After("execution(* by.it_academy.food_control.service.WeightService.saveWeightControl(..))")
    public void createProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            WeightControl weightControl = (WeightControl) args[0];
            Audit audit = new Audit();

            audit.setDate_event(weightControl.getDate_update());
            audit.setLog("Создание нового взвешивания " + weightControl.getNew_weight());

            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.WEIGHT_CONTROL);
            audit.setEntity_id(weightControl.getId());

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при создании взвешивания");
        }
    }

    @After("execution(* by.it_academy.food_control.service.WeightService.updateWeightControl(..))")
    public void updateProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            WeightControl weightControl = (WeightControl) args[0];
            Long weightControlId = (Long) args[1];
            Audit audit = new Audit();

            audit.setDate_event(weightControl.getDate_update());
            audit.setLog("Обновление взвешивания " + weightControl.getNew_weight());
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.WEIGHT_CONTROL);
            audit.setEntity_id(weightControlId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при обновлении продукта");
        }
    }

    @Before("execution(* by.it_academy.food_control.service.WeightService.deleteWeightControlById(..))")
    public void deleteProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Long weightControlId = (Long) args[0];
            Audit audit = new Audit();

            audit.setDate_event(LocalDateTime.now());
            audit.setLog("Удаление взвешивания " + weightControlId);
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.WEIGHT_CONTROL);
            audit.setEntity_id(weightControlId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при удалении взвешивания");
        }
    }
}
