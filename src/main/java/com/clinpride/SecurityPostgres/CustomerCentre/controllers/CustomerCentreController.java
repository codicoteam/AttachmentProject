package com.clinpride.SecurityPostgres.CustomerCentre.controllers;

import com.clinpride.SecurityPostgres.CustomerCentre.model.CustomerCentreModels;
import com.clinpride.SecurityPostgres.CustomerCentre.services.CustomerCentreServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/help-centre")
public class CustomerCentreController {
    private final CustomerCentreServices customerCentreServices;
    @PostMapping("/send-help-message")
    public ResponseEntity<CustomerCentreModels> createCategory(@RequestBody CustomerCentreModels customerCentreModels) {
        CustomerCentreModels savedProduct = customerCentreServices.createICustomer(customerCentreModels);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @GetMapping("/find-By-customer-email")
    public ResponseEntity<List<CustomerCentreModels>> getInvitesByCustomerEmail(@RequestParam String email) {
        List<CustomerCentreModels> orders = customerCentreServices.getByCustomerEmail(email);
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getAll")
    public List<CustomerCentreModels> getAllCustomerHelp() {
        return customerCentreServices.getAllCentreCustomer();
    }
    @DeleteMapping("/delete-customerCentre")
    public ResponseEntity<String> deleteCustomerHelp(@RequestBody List<String> packageIds) {
        customerCentreServices.deleteManyCustomerCentreByIds(packageIds);
        return ResponseEntity.ok("Customer Services deleted successfully");
    }
}
