package by.it_academy.food_control.service.api;

import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IProductService {

    Product getProductById(Long id_product);

    void saveProduct(Product new_product);

    void deleteProductById(Long id_product);

    Page<Product> getAllProduct(PagesDTO pagesDTO);

    void updateProduct(Product product_update, Long id);

    List<Product> getProductByName(String name);
}
