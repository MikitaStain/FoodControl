package by.it_academy.food_control.model;

import by.it_academy.food_control.model.api.exercises_name.EExercises;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private EExercises exercise_name;

    @Column
    private Double calories;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column
    private LocalDateTime training_date;

    @Column
    private LocalDateTime data_created;

    @Column
    private LocalDateTime data_update;

    public Exercise() {

        this.data_created = LocalDateTime.now();
        this.data_update = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public EExercises getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(EExercises exercise_name) {
        this.exercise_name = exercise_name;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public LocalDateTime getTraining_date() {
        return training_date;
    }

    public void setTraining_date(LocalDateTime training_date) {
        this.training_date = training_date;
    }

    public LocalDateTime getData_created() {
        return data_created;
    }

    public void setData_created(LocalDateTime data_created) {
        this.data_created = data_created;
    }

    public LocalDateTime getData_update() {
        return data_update;
    }

    public void setData_update(LocalDateTime data_update) {
        this.data_update = data_update;
    }
}
