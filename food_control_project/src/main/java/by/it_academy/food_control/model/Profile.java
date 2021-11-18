package by.it_academy.food_control.model;

import by.it_academy.food_control.model.api.gender.EGender;
import by.it_academy.food_control.model.api.goal.EGoal;
import by.it_academy.food_control.model.api.lifestyle.ELifestyle;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate date_of_birth;

    @Column
    private EGender gender;

    @Column
    private ELifestyle lifestyle;

    @Column
    private EGoal goal;

    @Column
    private LocalDateTime date_create;

    @Column
    private LocalDateTime date_update;

    public Profile() {

        this.date_create = LocalDateTime.now();
        this.date_update = LocalDateTime.now();
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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public ELifestyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(ELifestyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public EGoal getGoal() {
        return goal;
    }

    public void setGoal(EGoal goal) {
        this.goal = goal;
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
