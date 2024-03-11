package com.clinpride.SecurityPostgres.Quatation.Controllers;

import com.clinpride.SecurityPostgres.Quatation.Model.QuotationModel;
import com.clinpride.SecurityPostgres.Quatation.Services.QuotationServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/quotation")
public class QuotationController {
    private final QuotationServices quotationServices;
    @GetMapping("/getAll")
    public List<QuotationModel> getAllQuotation() {
        return quotationServices.getAllQuotation();
    }

    @PostMapping("/edit-quotation/{id}")
    public ResponseEntity<QuotationModel> updateQuotation(@PathVariable String id, @RequestBody QuotationModel updatedProduct) {
        Optional<QuotationModel> updatedProductOptional = quotationServices.editQuotation(id, updatedProduct);
        return updatedProductOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/delete-quotation/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        boolean isDeleted = quotationServices.deleteQuotation(id);
        if (isDeleted) {
            String response = "Quotation with ID " + id + " deleted successfully.";
            return ResponseEntity.ok(response);
        } else {
            String response = "Quotation with ID " + id + " does not exist.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);        }
    }

    @GetMapping("/find-quotation/{id}")
    public ResponseEntity<QuotationModel> getOneQuotation(@PathVariable String id) {
        Optional<QuotationModel> product = quotationServices.getOneQuotation(id);
        return product.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/search/by/customer-email")
    public List<QuotationModel> getBulkBuyingByFeature(@RequestParam("email") String email) {
        return quotationServices.getOneByEmail(email);
    }


    @PostMapping("/create-quotation")
    public ResponseEntity<QuotationModel> createQuotation(@RequestBody QuotationModel quotationModel) {
        QuotationModel savedProduct = quotationServices.createQuotation(quotationModel);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-quotation")
    public ResponseEntity<String> deleteQuotations(@RequestBody List<String> packageIds) {
        quotationServices.deleteQuotation(packageIds);
        return ResponseEntity.ok("Quotations deleted successfully");
    }
}
