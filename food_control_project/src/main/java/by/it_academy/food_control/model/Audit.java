package by.it_academy.food_control.model;

import by.it_academy.food_control.model.api.entity_type.EEntity_type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audits")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String log;

    @Column
    @Enumerated(EnumType.STRING)
    private EEntity_type entity_type;

    @Column
    private Long entity_id;

    @Column
    private LocalDateTime date_event;

    public Audit() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public EEntity_type getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(EEntity_type entity_type) {
        this.entity_type = entity_type;
    }

    public Long getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(Long entity_id) {
        this.entity_id = entity_id;
    }

    public LocalDateTime getDate_event() {
        return date_event;
    }

    public void setDate_event(LocalDateTime date_event) {
        this.date_event = date_event;
    }
}
