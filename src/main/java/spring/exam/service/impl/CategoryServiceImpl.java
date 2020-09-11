package spring.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.exam.model.entity.Category;
import spring.exam.model.entity.CategoryName;
import spring.exam.model.service.CategoryServiceModel;
import spring.exam.repository.CategoryRepository;
import spring.exam.service.CategoryService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initCategories() {
        if(this.categoryRepository.count() == 0){
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository
                                .save(new Category(categoryName,
                                        String.format("Description for %s"
                                                , categoryName.name())));
                    });
        }
    }

    @Override
    public CategoryServiceModel find(CategoryName categoryName) {
        return this.categoryRepository
                .findByName(categoryName)
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }

  
}
