package com.clinpride.SecurityPostgres.CustomerCentre.repository;

import com.clinpride.SecurityPostgres.CustomerCentre.model.CustomerCentreModels;
import com.clinpride.SecurityPostgres.InviteUser.models.InviteUserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCentreRepo extends MongoRepository<CustomerCentreModels, String> {
    void deleteByIdIn(List<String> productIds);
    List<CustomerCentreModels> findByCustomerEmail(String email);
}
