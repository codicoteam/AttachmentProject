package com.clinpride.SecurityPostgres.BulkProducts.Repository;

import com.clinpride.SecurityPostgres.BulkProducts.Models.BulkBuying;
import com.clinpride.SecurityPostgres.FurniturePackage.Models.PackageModels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BulkBuyingRepo extends MongoRepository<BulkBuying, String> {
    void deleteByIdIn(List<String> productIds);
    List<BulkBuying> findByBulkBuyingCategoriesCategoryName(String categoryName);
    List<BulkBuying> findByBulkBuyingAttributesColor(String color);
    List<BulkBuying> findByBulkBuyingAttributesType(String type);


    List<BulkBuying> findByWholesaleEmail(String type);

    List<BulkBuying> findByBulkBuyingPriceBetween(double minPrice, double maxPrice);
    List<BulkBuying> findByBulkBuyingAttributesFeature(String feature);
    List<BulkBuying> findByBulkBuyingDiscountBetween(double minDiscount, double maxDiscount);
}
