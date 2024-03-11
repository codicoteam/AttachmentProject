package com.clinpride.SecurityPostgres.Category.Controllers;

import com.clinpride.SecurityPostgres.Category.Models.CategoryModel;
import com.clinpride.SecurityPostgres.Category.Services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class ControllerCategory {
    private final CategoryService categoryService;
    @GetMapping("/getAll")
    public List<CategoryModel> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @PostMapping("/edit-category/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable String id, @RequestBody CategoryModel updatedProduct) {
        Optional<CategoryModel> updatedProductOptional = categoryService.editCategory(id, updatedProduct);
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/delete-category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            String response = "Product with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Product with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @GetMapping("/find-category/{id}")
    public ResponseEntity<CategoryModel> getOneCategory(@PathVariable String id) {
        Optional<CategoryModel> product = categoryService.getOneCategory(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-category")
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel oDisplayModels) {
        CategoryModel savedProduct = categoryService.createCategory(oDisplayModels);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-categories")
    public ResponseEntity<String> deletePackages(@RequestBody List<String> packageIds) {
        categoryService.deletePackageByIds(packageIds);
        return ResponseEntity.ok("Categories deleted successfully");
    }

}
