package com.clinpride.SecurityPostgres.Quatation.Repository;

import com.clinpride.SecurityPostgres.Quatation.Model.QuotationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationRepo extends MongoRepository<QuotationModel, String> {

   List <QuotationModel> findByCustomerEmail(String customerEmail);
    void deleteByIdIn(List<String> productIds);
}
