package by.it_academy.food_control.service.audit;

import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.model.Product;
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
public class ProductAudit {

    private final IAuditService auditService;
    private final UserHolder userHolder;


    public ProductAudit(IAuditService auditService, UserHolder userHolder) {

        this.auditService = auditService;
        this.userHolder = userHolder;

    }

    @After("execution(* by.it_academy.food_control.service.ProductService.saveProduct(..))")
    public void createProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];
            Audit audit = new Audit();

            audit.setDate_event(product.getData_update());
            audit.setLog("Создание нового продукта " + product.getNameProduct());

            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.PRODUCT);
            audit.setEntity_id(product.getId());

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при создании продукта");
        }
    }

    @After("execution(* by.it_academy.food_control.service.ProductService.updateProduct(..))")
    public void updateProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Product product = (Product) args[0];
            Long productId = (Long) args[1];
            Audit audit = new Audit();

            audit.setDate_event(product.getData_update());
            audit.setLog("Обновление продукта " + product.getNameProduct());
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.PRODUCT);
            audit.setEntity_id(productId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при обновлении продукта");
        }
    }

    @Before("execution(* by.it_academy.food_control.service.ProductService.deleteProductById(..))")
    public void deleteProductLogs(JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();

            Long productId = (Long) args[0];
            Audit audit = new Audit();

            audit.setDate_event(LocalDateTime.now());
            audit.setLog("Удаление продукта " + productId);
            audit.setUser(userHolder.getUser());
            audit.setEntity_type(EEntity_type.PRODUCT);
            audit.setEntity_id(productId);

            auditService.saveAudit(audit);

        } catch (Throwable e) {
            throw new IllegalArgumentException("ошибка аудита при Удалении продукта");
        }
    }
}
