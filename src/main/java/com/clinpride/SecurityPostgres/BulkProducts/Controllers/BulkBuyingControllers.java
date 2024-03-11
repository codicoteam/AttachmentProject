package com.clinpride.SecurityPostgres.BulkProducts.Controllers;

import com.clinpride.SecurityPostgres.BulkProducts.Models.BulkBuying;
import com.clinpride.SecurityPostgres.BulkProducts.Services.BulkBuyingServices;
import com.clinpride.SecurityPostgres.FurniturePackage.Models.PackageModels;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bulk-buying")
public class BulkBuyingControllers {
    private final BulkBuyingServices bulkBuyingServices;

    @PostMapping("/create-bulk-buying")
    public ResponseEntity<BulkBuying> createBulkBuying(@RequestBody BulkBuying bulkBuyingModel) {
        BulkBuying savedPackage = bulkBuyingServices.createBulkBuyingModel(bulkBuyingModel);
        return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-bulk-buying")
    public List<BulkBuying> getAllBulkBuying() {
        return bulkBuyingServices.getAllBulkBuyingModel();
    }


    @GetMapping("/find-bulk-buying/{id}")
    public ResponseEntity<BulkBuying> getBulkBuyingById(@PathVariable String id) {
        Optional<BulkBuying> product = bulkBuyingServices.getOneBulkBuyingModel(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/delete-bulk-buying/{id}")
    public ResponseEntity<String> deleteBulkBuyingById(@PathVariable String id) {
        boolean isDeleted = bulkBuyingServices.deleteBulkBuyingModel(id);
        if (isDeleted) {
            String response = "bulk buying with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "bulk-buying with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }
    @DeleteMapping("/delete-bulk-buying")
    public ResponseEntity<String> deleteBulkBuying(@RequestBody List<String> bulkBuyingIds) {
        bulkBuyingServices.deleteBulkBuyingByIds(bulkBuyingIds);
        return ResponseEntity.ok("bulk-buying deleted successfully");
    }

    @PostMapping("/edit-bulk-buying/{id}")
    public ResponseEntity<BulkBuying> updateBulkBuying(@PathVariable String id, @RequestBody BulkBuying bulkBuyingModel) {
        Optional<BulkBuying> updatedProductOptional = bulkBuyingServices.editBulkBuying(id, bulkBuyingModel);
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryName}")
    public List<BulkBuying> getBulkBuyingByCategoryName(@PathVariable String categoryName) {
        return bulkBuyingServices.searchBulkBuyingModelCategory(categoryName);
    }

    @GetMapping("/color/{color}")
    public List<BulkBuying> getBulkBuyingByColor(@PathVariable String color) {
        return bulkBuyingServices.searchBulkBuyingModelColor(color);
    }

    @GetMapping("/type/{type}")
    public List<BulkBuying> getBulkBuyingByType(@PathVariable String type) {
        return bulkBuyingServices.searchBulkBuyingModelType(type);
    }

    @GetMapping("/getproductby/pricerange")
    public List<BulkBuying> getBulkBuyingByPriceRange(@RequestParam("minPrice") double minPrice,
                                                       @RequestParam("maxPrice") double maxPrice) {
        return bulkBuyingServices.RangerBulkBuyingModel(minPrice, maxPrice);
    }
    @GetMapping("/search/by/feature")
    public List<BulkBuying> getBulkBuyingByFeature(@RequestParam("feature") String feature) {
        return bulkBuyingServices.searchByFeature(feature);
    }

    @GetMapping("/get/product/by/bulkBuying/discount")
    public List<BulkBuying> getBulkBuyingByDiscountRange(@RequestParam("minDiscount") double minDiscount,
                                                          @RequestParam("maxDiscount") double maxDiscount) {
        return bulkBuyingServices.RangerDiscount(minDiscount, maxDiscount);
    }
    @GetMapping("/wholesale-email/{color}")
    public List<BulkBuying> get(@PathVariable String color) {
        return bulkBuyingServices.searchWholeSaleName(color);
    }

}
