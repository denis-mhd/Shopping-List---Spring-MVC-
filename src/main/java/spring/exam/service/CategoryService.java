package spring.exam.service;

import spring.exam.model.entity.Category;
import spring.exam.model.entity.CategoryName;
import spring.exam.model.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    void initCategories();

    CategoryServiceModel find(CategoryName categoryName);



}
