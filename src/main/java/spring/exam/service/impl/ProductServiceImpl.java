package spring.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.exam.model.binding.ProductAddBindingModel;
import spring.exam.model.entity.Category;
import spring.exam.model.entity.Product;
import spring.exam.model.service.ProductServiceModel;
import spring.exam.repository.ProductRepository;
import spring.exam.service.CategoryService;
import spring.exam.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductAddBindingModel productAddBindingModel) {
        ProductServiceModel productServiceModel = this.modelMapper.map(productAddBindingModel, ProductServiceModel.class);
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setCategory(this.modelMapper.map(this.categoryService.find(productServiceModel.getCategory().getCategoryName()), Category.class));
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductServiceModel> getAll() {
        return this.productRepository
                .findAll()
                .stream()
                .map(e -> this.modelMapper.map(e, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(null);
        this.productRepository.delete(product);
    }

    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }

}
