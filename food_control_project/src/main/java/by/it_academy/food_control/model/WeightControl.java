package by.it_academy.food_control.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weight_controls")
public class WeightControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column
    private Double new_weight;

    @Column
    private LocalDateTime weigh_in_date;

    @Column
    private LocalDateTime date_create;

    @Column
    private LocalDateTime date_update;

    public WeightControl() {

        this.date_create = LocalDateTime.now();
        this.date_update = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Double getNew_weight() {
        return new_weight;
    }

    public void setNew_weight(Double new_weight) {
        this.new_weight = new_weight;
    }

    public LocalDateTime getWeigh_in_date() {
        return weigh_in_date;
    }

    public void setWeigh_in_date(LocalDateTime weigh_in_date) {
        this.weigh_in_date = weigh_in_date;
    }

    public LocalDateTime getDate_create() {
        return date_create;
    }

    public void setDate_create(LocalDateTime date_create) {
        this.date_create = date_create;
    }

    public LocalDateTime getDate_update() {
        return date_update;
    }

    public void setDate_update(LocalDateTime date_update) {
        this.date_update = date_update;
    }
}
