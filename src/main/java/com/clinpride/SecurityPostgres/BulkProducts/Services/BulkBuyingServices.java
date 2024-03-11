package com.clinpride.SecurityPostgres.BulkProducts.Services;

import com.clinpride.SecurityPostgres.BulkProducts.Models.BulkBuying;

import java.util.List;
import java.util.Optional;

public interface BulkBuyingServices {
    Optional<BulkBuying> editBulkBuying(String id, BulkBuying bulkBuyingModel);
    boolean deleteBulkBuyingByIds(List<String> bulkBuyingId);
    boolean deleteBulkBuyingModel(String id);
    BulkBuying createBulkBuyingModel(BulkBuying bulkBuyingModel);
    List<BulkBuying> getAllBulkBuyingModel();
    Optional<BulkBuying> getOneBulkBuyingModel(String id);
    List<BulkBuying> searchBulkBuyingModelCategory(String categoryName);
    List<BulkBuying> searchBulkBuyingModelColor(String color);
    List<BulkBuying> searchBulkBuyingModelType(String type);
    List<BulkBuying> RangerBulkBuyingModel(double minPrice,double maxPrice);
    List<BulkBuying> searchByFeature(String feature);
    List<BulkBuying> RangerDiscount(double minPrice,double maxPrice);
    List<BulkBuying> searchWholeSaleName(String type);
}
