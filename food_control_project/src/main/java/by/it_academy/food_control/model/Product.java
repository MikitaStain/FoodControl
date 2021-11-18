package by.it_academy.food_control.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Name product should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String nameProduct;

    @Column
    @Size(min = 2, max = 50, message = "Brand should be between 2 and 50 characters")
    private String brand;

    @Column
    @Min(value = 0, message = "")
    @Max(value = 10000, message = "")
    private Double calories;

    @Column
    @Min(value = 0, message = "")
    @Max(value = 10000, message = "")
    @NotNull(message = "proteins product should not be empty")
    private Double proteins;

    @Column
    @Min(value = 0, message = "")
    @Max(value = 10000, message = "")
    private Double fats;

    @Column
    @Min(value = 0, message = "")
    @Max(value = 10000, message = "")
    private Double carbohydrates;

    @Column
    @Min(value = 0, message = "")
    @Max(value = 1000, message = "")
    private Double measure;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime data_created;

    @JsonFormat(shape = JsonFormat.Shape.NATURAL, pattern = "yyyyMMddHHmmss[SSS]")
    @Column
    private LocalDateTime data_update;


    public Product() {
        this.data_created = LocalDateTime.now();
        this.data_update = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getMeasure() {
        return measure;
    }

    public void setMeasure(Double measure) {
        this.measure = measure;
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
