package com.clinpride.SecurityPostgres.BulkProducts.Services;

import com.clinpride.SecurityPostgres.BulkProducts.Models.BulkBuying;
import com.clinpride.SecurityPostgres.BulkProducts.Repository.BulkBuyingRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BulkBuyingServicesImpl implements BulkBuyingServices{

    private  final BulkBuyingRepo bulkBuyingRepo;
    @Override
    public Optional<BulkBuying> editBulkBuying(String id, BulkBuying bulkBuyingModel) {
        Optional<BulkBuying> productOptional = bulkBuyingRepo.findById(id);
        if (productOptional.isPresent()) {
            BulkBuying bulkBuyings = productOptional.get();
            bulkBuyings.setBulkSalesPrice(bulkBuyingModel.getBulkSalesPrice());
            bulkBuyings.setColors(bulkBuyingModel.getColors());
            bulkBuyings.setBulkBuyingCategories(bulkBuyingModel.getBulkBuyingCategories());
            bulkBuyings.setImages(bulkBuyingModel.getImages());
            bulkBuyings.setBulkBuyingLikes(bulkBuyingModel.getBulkBuyingLikes());
            bulkBuyings.setBulkBuyingName(bulkBuyingModel.getBulkBuyingName());
            bulkBuyings.setStatus(bulkBuyingModel.getStatus());
            bulkBuyings.setContent(bulkBuyingModel.getContent());
            bulkBuyings.setImages(bulkBuyingModel.getImages());
            bulkBuyings.setRegularPrice(bulkBuyingModel.getRegularPrice());
            bulkBuyings.setBulkPurchasable(bulkBuyingModel.getBulkPurchasable());
            bulkBuyings.setStatus(bulkBuyingModel.getStatus());
            bulkBuyings.setReviews(bulkBuyingModel.getReviews());
            bulkBuyings.setRatings(bulkBuyingModel.getRatings());
            bulkBuyings.setContent(bulkBuyingModel.getContent());
            bulkBuyings.setExplainRowMaterials(bulkBuyingModel.getExplainRowMaterials());
            bulkBuyings.setPackageQuantity(bulkBuyingModel.getPackageQuantity());
            bulkBuyings.setBulkBuyingWarranty(bulkBuyingModel.getBulkBuyingWarranty());
            bulkBuyings.setExplainRowMaterials(bulkBuyingModel.getExplainRowMaterials());
            bulkBuyings.setBulkBuyingDescription(bulkBuyingModel.getBulkBuyingDescription());
            bulkBuyings.setRowMaterials(bulkBuyingModel.getRowMaterials());
            bulkBuyings.setBulkLocation(bulkBuyingModel.getBulkLocation());
            BulkBuying savedProduct = bulkBuyingRepo.save(bulkBuyings);
            return Optional.of(savedProduct);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteBulkBuyingByIds(List<String> bulkBuyingId) {
        bulkBuyingRepo.deleteByIdIn(bulkBuyingId);
        return true;
    }

    @Override
    public boolean deleteBulkBuyingModel(String id) {
        Optional<BulkBuying> productOptional = bulkBuyingRepo.findById(id);
        if (productOptional.isPresent()) {
            bulkBuyingRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public BulkBuying createBulkBuyingModel(BulkBuying bulkBuyingModel) {
        return bulkBuyingRepo.save(bulkBuyingModel);
    }

    @Override
    public List<BulkBuying> getAllBulkBuyingModel() {
        return bulkBuyingRepo.findAll();
    }

    @Override
    public Optional<BulkBuying> getOneBulkBuyingModel(String id) {
        return bulkBuyingRepo.findById(id);
    }

    @Override
    public List<BulkBuying> searchBulkBuyingModelCategory(String categoryName) {
        return bulkBuyingRepo.findByBulkBuyingCategoriesCategoryName(categoryName);
    }

    @Override
    public List<BulkBuying> searchBulkBuyingModelColor(String color) {
        return bulkBuyingRepo.findByBulkBuyingAttributesColor(color);
    }

    @Override
    public List<BulkBuying> searchBulkBuyingModelType(String type) {
        return bulkBuyingRepo.findByBulkBuyingAttributesType(type);
    }

    @Override
    public List<BulkBuying> RangerBulkBuyingModel(double minPrice, double maxPrice) {
        return bulkBuyingRepo.findByBulkBuyingPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<BulkBuying> searchByFeature(String feature) {
        return bulkBuyingRepo.findByBulkBuyingAttributesFeature(feature);
    }

    @Override
    public List<BulkBuying> RangerDiscount(double minPrice, double maxPrice) {
        return bulkBuyingRepo.findByBulkBuyingDiscountBetween(minPrice, maxPrice);
    }

    @Override
    public List<BulkBuying> searchWholeSaleName(String type) {
        return bulkBuyingRepo.findByWholesaleEmail(type);
    }
}
