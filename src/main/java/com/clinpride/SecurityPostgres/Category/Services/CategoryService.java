package com.clinpride.SecurityPostgres.Category.Services;

import com.clinpride.SecurityPostgres.Category.Models.CategoryModel;
import com.clinpride.SecurityPostgres.OnDisplay.Models.OnDisplayModels;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    Optional<CategoryModel> editCategory(String id, CategoryModel categoryModel);
    boolean deleteCategory(String id);
    CategoryModel createCategory(CategoryModel categoryModel);
    List<CategoryModel> getAllCategory();
    Optional<CategoryModel> getOneCategory(String id);
    boolean deletePackageByIds(List<String> packageIds);
}
