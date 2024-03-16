package com.clinpride.SecurityPostgres.MakePayment.Repository;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePaymentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MakePaymentRepo extends MongoRepository<MakePaymentModel, String> {
    void deleteByIdIn(List<String> makePaymentIds);
    List<MakePaymentModel> findByCustomerEmail(String customerEmail);
}
