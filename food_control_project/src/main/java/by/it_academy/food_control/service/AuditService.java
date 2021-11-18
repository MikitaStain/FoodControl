package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IAuditDAO;
import by.it_academy.food_control.model.Audit;
import by.it_academy.food_control.service.api.IAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService implements IAuditService {

    private final IAuditDAO auditDAO;

    @Autowired
    public AuditService(IAuditDAO auditDAO) {
        this.auditDAO = auditDAO;
    }

    @Override
    public Audit getAuditById(Long id_audit) {

      return auditDAO.findById(id_audit).orElseThrow(
              ()-> new IllegalArgumentException("Аудит по id не найден"));
    }

    @Override
    public void saveAudit(Audit new_audit) {

        this.auditDAO.save(new_audit);
    }

    @Override
    public void deleteAuditById(Long id_audit) {

        getAuditById(id_audit);

        this.auditDAO.deleteById(id_audit);
    }

    @Override
    public List<Audit> getAllAudit() {
        return this.auditDAO.findAll();
    }

    @Override
    public void updateAudit(Audit audit_update, Long id) {



    }

    @Override
    public List<Audit> getAuditByName(String name_audit) {
        return null;
    }
}
