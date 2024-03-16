package com.clinpride.SecurityPostgres.MakePayment.Model;

import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Data
@Document(collection = "Payments")
public class MakePaymentModel {
    @Id
    private String id;
    private String paymentTotal;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerFullName;
    private String customerAddress;
    private String invoiceNumber;
    private String paymentMethod;
    private Boolean booleanMakePayments =false;
    private String dateNow;
    private String pollUrl;
    private List<MakePaymentModel.PriceProductsAndNumber> products;
    @Data
    public static class PriceProductsAndNumber {
        private String id;
        private double price;
        private String nameOfProduct;
    }
    public MakePaymentModel() {
        this.dateNow = LocalDate.now().toString();
    }
}
