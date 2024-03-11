package com.clinpride.SecurityPostgres.Category.Repository;

import com.clinpride.SecurityPostgres.Category.Models.CategoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends MongoRepository<CategoryModel, String> {
    void deleteByIdIn(List<String> productIds);

}
