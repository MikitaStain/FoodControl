package by.it_academy.food_control.service.api;

import by.it_academy.food_control.model.Audit;

import java.util.List;

public interface IAuditService {

    Audit getAuditById(Long id_audit);

    void saveAudit(Audit new_audit);

    void deleteAuditById(Long id_audit);

    List<Audit> getAllAudit();

    void updateAudit(Audit audit_update, Long id);

    List<Audit> getAuditByName(String name_audit);
}
