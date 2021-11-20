package by.it_academy.food_control.service.audit;

import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.model.Exercise;
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
public class ExerciseAudit {


    private final IAuditService auditService;
    private final UserHolder userHolder;


    public ExerciseAudit(IAuditService auditService, UserHolder userHolder) {

        this.auditService = auditService;
        this.userHolder = userHolder;

    }

    @After("execution(* by.it_academy.food_control.service.ExerciseService.saveExercise(..))")
    public void createProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Exercise exercise = (Exercise) args[0];
            Audit audit = new Audit();

            audit.setDate_event(exercise.getData_update());
            audit.setLog("Создание нового упражнения " + exercise.getExercise_name());

            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.EXERCISE);
            audit.setEntity_id(exercise.getId());

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при создании упражнения");
        }
    }

    @After("execution(* by.it_academy.food_control.service.ExerciseService.updateExercise(..))")
    public void updateProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Exercise exercise = (Exercise) args[0];
            Long exerciseId = (Long) args[1];
            Audit audit = new Audit();

            audit.setDate_event(exercise.getData_update());
            audit.setLog("Обновление упражнения " + exercise.getExercise_name());
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.EXERCISE);
            audit.setEntity_id(exerciseId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при обновлении упражнения");
        }
    }

    @Before("execution(* by.it_academy.food_control.service.ExerciseService.deleteExerciseById(..))")
    public void deleteProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Long exerciseId = (Long) args[0];
            Audit audit = new Audit();

            audit.setDate_event(LocalDateTime.now());
            audit.setLog("Удаление упражнения " + exerciseId);
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.EXERCISE);
            audit.setEntity_id(exerciseId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при удалении упражнения");
        }
    }
}
