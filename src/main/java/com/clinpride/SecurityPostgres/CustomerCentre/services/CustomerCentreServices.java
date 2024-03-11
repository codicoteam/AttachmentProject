package com.clinpride.SecurityPostgres.CustomerCentre.services;

import com.clinpride.SecurityPostgres.CustomerCentre.model.CustomerCentreModels;
import com.clinpride.SecurityPostgres.InviteUser.models.InviteUserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerCentreServices {
    public CustomerCentreModels createICustomer(CustomerCentreModels customerCentre);
    List<CustomerCentreModels> getByCustomerEmail(String Email);
    List<CustomerCentreModels> getAllCentreCustomer();
    boolean deleteManyCustomerCentreByIds(List<String> packageIds);
}
