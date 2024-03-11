package com.clinpride.SecurityPostgres.Quatation.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(collection = "quotation")
public class QuotationModel {
   @Id
   private String id;
   private String title;
   private String message;
   private int quantity;
   private int expectedDeposit;
   private String customerNumber;
   private String customerEmail;
   private String rowMaterials;
   private String customerName;
   private String showQuotation;
   private String doYouHaveMaterials;
   private String expectedBudget;
}
