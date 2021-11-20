package by.it_academy.food_control.service;

import by.it_academy.food_control.dao.api.IProductDAO;
import by.it_academy.food_control.model.Product;
import by.it_academy.food_control.security.UserHolder;
import by.it_academy.food_control.service.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {


    private final IProductDAO productDAO;
    private final UserHolder userHolder;

    @Autowired
    public ProductService(IProductDAO productDAO, UserHolder userHolder) {
        this.productDAO = productDAO;
        this.userHolder = userHolder;
    }

    @Override
    public Product getProductById(Long id_product) {

        return productDAO.findById(id_product).orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));
    }

    @Override
    public void saveProduct(Product new_product) {

        new_product.setUser(userHolder.getUser());

        this.productDAO.save(new_product);
    }

    @Override
    public void deleteProductById(Long id_product) {

        this.productDAO.deleteById(id_product);

    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.findAll();
    }

    @Override
    public void updateProduct(Product product_update, Long id) {

        Product product = getProductById(id);

        product.setNameProduct(product_update.getNameProduct());
        product.setBrand(product_update.getBrand());
        product.setCalories(product_update.getCalories());
        product.setProteins(product_update.getProteins());
        product.setFats(product_update.getFats());
        product.setCarbohydrates(product_update.getCarbohydrates());
        product.setMeasure(product_update.getMeasure());
        product.setData_update(LocalDateTime.now());

        this.productDAO.saveAndFlush(product);
    }

    @Override
    public List<Product> getProductByName(String name) {

        return productDAO.findByNameProduct(name);
    }
}
