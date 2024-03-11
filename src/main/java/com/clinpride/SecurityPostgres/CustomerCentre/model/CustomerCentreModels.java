package com.clinpride.SecurityPostgres.CustomerCentre.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "customerCentre")
public class CustomerCentreModels {
    @Id
    private String id;
    private String message;
    private Boolean showCustomerCentre;
    private String customerName;
    private String customerEmail;
    private String customerNumber;
}
