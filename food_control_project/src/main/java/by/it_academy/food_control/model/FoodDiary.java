package by.it_academy.food_control.model;

import by.it_academy.food_control.model.api.meal_time.EMeal_time;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "food_diaries")
public class FoodDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column
    private Double weight;

    @Column
    private LocalDateTime data_create;

    @Column
    private LocalDateTime data_update;

    @Column
    private EMeal_time meal_time_type;

    @Column
    private LocalDateTime meal_time_date;

    public FoodDiary() {

        this.data_create = LocalDateTime.now();
        this.data_update = LocalDateTime.now();
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDateTime getData_create() {
        return data_create;
    }

    public void setData_create(LocalDateTime data_create) {
        this.data_create = data_create;
    }

    public EMeal_time getMeal_time_type() {
        return meal_time_type;
    }

    public void setMeal_time_type(EMeal_time meal_time_type) {
        this.meal_time_type = meal_time_type;
    }

    public LocalDateTime getMeal_time_date() {
        return meal_time_date;
    }

    public void setMeal_time_date(LocalDateTime meal_time) {
        this.meal_time_date = meal_time;
    }

    public LocalDateTime getData_update() {
        return data_update;
    }

    public void setData_update(LocalDateTime data_update) {
        this.data_update = data_update;
    }
}
