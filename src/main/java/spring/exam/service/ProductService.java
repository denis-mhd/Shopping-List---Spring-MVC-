package spring.exam.service;

import spring.exam.model.binding.ProductAddBindingModel;
import spring.exam.model.entity.Product;
import spring.exam.model.service.ProductServiceModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductAddBindingModel productAddBindingModel);

    List<ProductServiceModel> getAll();

    void delete(String id);


    void deleteAll();
}
