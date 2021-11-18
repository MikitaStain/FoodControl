package by.it_academy.food_control.dao.api;

import by.it_academy.food_control.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductDAO extends JpaRepository<Product, Long> {

    List<Product> findByNameProduct(String name_product);
}
