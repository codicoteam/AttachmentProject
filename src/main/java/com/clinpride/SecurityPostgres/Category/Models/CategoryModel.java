package com.clinpride.SecurityPostgres.Category.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "Categories")
public class CategoryModel {
    @Id
    private String id;
    private String categoryName;
    private String categoryImagePath;
    private String showCategory;

}
