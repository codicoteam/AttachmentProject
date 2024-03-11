package com.clinpride.SecurityPostgres.InviteUser.repository;

import com.clinpride.SecurityPostgres.InviteUser.models.InviteUserModel;
import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InviteUserRepo extends MongoRepository<InviteUserModel, String> {
    void deleteByIdIn(List<String> productIds);
    List<InviteUserModel> findByInvitingEmail(String email);

}
