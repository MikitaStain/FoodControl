package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuditDAO extends JpaRepository<Audit, Long> {
}
