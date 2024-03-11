package com.clinpride.SecurityPostgres.CustomerCentre.services;

import com.clinpride.SecurityPostgres.CustomerCentre.model.CustomerCentreModels;
import com.clinpride.SecurityPostgres.CustomerCentre.repository.CustomerCentreRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerCentreServicesImpl implements CustomerCentreServices{
    private final CustomerCentreRepo customerCentreRepo;

    @Override
    public CustomerCentreModels createICustomer(CustomerCentreModels customerCentre) {
        return customerCentreRepo.save(customerCentre);
    }

    @Override
    public List<CustomerCentreModels> getByCustomerEmail(String Email) {
        return customerCentreRepo.findByCustomerEmail(Email);
    }

    @Override
    public List<CustomerCentreModels> getAllCentreCustomer() {
        return customerCentreRepo.findAll();
    }

    @Override
    public boolean deleteManyCustomerCentreByIds(List<String> packageIds) {
        try {
            customerCentreRepo.deleteByIdIn(packageIds);
            return true; // Deletion successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }
}
