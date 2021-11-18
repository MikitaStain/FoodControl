package by.it_academy.food_control.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name_dish;

    @OneToMany
    @JoinColumn(name = "dishes_id")
    private List<Ingredient> ingredients;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime data_created;

    @Column
    private LocalDateTime data_update;

    public Dish() {
        this.data_created = LocalDateTime.now();
        this.data_update = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName_dish() {
        return name_dish;
    }

    public void setName_dish(String name_dish) {
        this.name_dish = name_dish;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
