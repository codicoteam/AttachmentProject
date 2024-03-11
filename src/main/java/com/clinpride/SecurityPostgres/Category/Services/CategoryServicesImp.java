package com.clinpride.SecurityPostgres.Category.Services;

import com.clinpride.SecurityPostgres.Category.Models.CategoryModel;
import com.clinpride.SecurityPostgres.Category.Repository.CategoryRepo;
import com.clinpride.SecurityPostgres.OnDisplay.Models.OnDisplayModels;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServicesImp implements CategoryService{
    private final CategoryRepo categoryRepo;

    @Override
    public Optional<CategoryModel> editCategory(String id, CategoryModel categoryModel) {
        Optional<CategoryModel> productOptional = categoryRepo.findById(id);
        if (productOptional.isPresent()) {
            CategoryModel category = productOptional.get();
            category.setCategoryName(categoryModel.getCategoryName());
            category.setCategoryImagePath(categoryModel.getCategoryImagePath());
            category.setShowCategory(categoryModel.getShowCategory());
            CategoryModel savedProduct = categoryRepo.save(category);
            return Optional.of(savedProduct);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCategory(String id) {
        Optional<CategoryModel> productOptional = categoryRepo.findById(id);
        if (productOptional.isPresent()) {
            categoryRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public CategoryModel createCategory(CategoryModel categoryModel) {
        return categoryRepo.save(categoryModel);
    }

    @Override
    public List<CategoryModel> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<CategoryModel> getOneCategory(String id) {
        return categoryRepo.findById(id);
    }

    @Override
    public boolean deletePackageByIds(List<String> packageIds) {
        categoryRepo.deleteByIdIn(packageIds);
        return true;
    }
}
