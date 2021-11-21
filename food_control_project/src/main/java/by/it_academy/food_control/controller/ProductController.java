package by.it_academy.food_control.controller;

import by.it_academy.food_control.dao.api.IProductDAO;
import by.it_academy.food_control.dto.PagesDTO;
import by.it_academy.food_control.model.Product;
import by.it_academy.food_control.service.api.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService, IProductDAO productDAO) {

        this.productService = productService;

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") Long product_id) {

        if (product_id == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            Product product = this.productService.getProductById(product_id);
            return new ResponseEntity<>(product, HttpStatus.OK);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //TODO валидация
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> save_new_product(@RequestBody @Valid Product product) {


        this.productService.saveProduct(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //TODO оптимистическая блокировка
    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id_product,
                                               @PathVariable("dt_update") Long localDateTime) {

        if (id_product == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            this.productService.deleteProductById(id_product);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProduct(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        PagesDTO pagesDTO = new PagesDTO();
        pagesDTO.setPageNumber(pageNumber);
        pagesDTO.setPageSize(pageSize);

        Page<Product> page = this.productService.getAllProduct(pagesDTO);
        List<Product> products = page.getContent();

        if (products.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //TODO подумать еще
    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id_product,
                                           @PathVariable("dt_update") Long last_date_update,
                                           @RequestBody @Valid Product product) {

        if (id_product == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            this.productService.updateProduct(product, id_product);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
