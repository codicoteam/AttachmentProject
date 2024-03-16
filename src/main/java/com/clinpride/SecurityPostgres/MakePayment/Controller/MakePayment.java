package com.clinpride.SecurityPostgres.MakePayment.Controller;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePaymentModel;
import com.clinpride.SecurityPostgres.MakePayment.Services.MakePaymentService;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.core.Payment;
import zw.co.paynow.responses.StatusResponse;
import zw.co.paynow.responses.WebInitResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/make-payment")
public class MakePayment {
     public final MakePaymentService makePaymentService;
    @PostMapping("/buy-goods")
    public void sendPayment(@RequestBody MakePaymentModel makePaymentModel) {

        Paynow paynow = new Paynow("16739", "7d15ff0f-9a44-4767-897f-f6b41a33fcc5");
        Payment payment = paynow.createPayment("invoice 23", "codicosoftwares@gmail.com");

        // Add items to the payment

        // Passing in the name of the item and the price of the item
        payment.add("Bananas", 2.5);
        payment.add("Apples", 3.4);

        //Initiating the transaction
        WebInitResponse response = paynow.send(payment);

        //If a mobile transaction,
        //MobileInitResponse response = paynow.sendMobile(payment, "0771234567", MobileMoneyMethod.ECOCASH);

        if (response.isRequestSuccess()) {
            // Get the url to redirect the user to so they can make payment
            String redirectURL = response.redirectURL();
            System.out.println(redirectURL);

            // Get the poll url of the transaction
            String pollUrl = response.pollUrl();

            //checking if the payment has been paid
            StatusResponse status = paynow.pollTransaction(pollUrl);
            System.out.println("pollurl: "+pollUrl);

            System.out.println(status);

            if (status.paid()) {
                // Yay! Transaction was paid for
            } else {
                System.out.println("Why you no pay?");
            }

        } else {
            // Something went wrong
            System.out.println(response.errors());
        }

    }


    @PostMapping("/buy-goods/with-web")
    public ResponseEntity<String> makeWebPayments(@RequestBody MakePaymentModel makePaymentModel) {
        try{
            String redirectingLink = makePaymentService.createPayment(makePaymentModel);
            return ResponseEntity.ok(redirectingLink);

        }catch(Exception e){
            String response = e.toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

    }

    @PostMapping("/buy-goods/with-ecocash")
    public ResponseEntity<String> makeEcoCashPayments(@RequestBody MakePaymentModel makePaymentModel) {
        try{
            System.out.println(makePaymentModel);
            String response = makePaymentService.createEcoCashPayment(makePaymentModel);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            String response = e.toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }
    @DeleteMapping("/delete-many-payments-by-id")
    public ResponseEntity<String> deleteBulkBuying(@RequestBody List<String> payments) {
        try{
            System.out.println(payments);
            makePaymentService.deleteManyByIds(payments);
            return ResponseEntity.ok("many payments deleted successfully");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.toString());
        }
    }
    @GetMapping("/get-all-payments")
    public List<MakePaymentModel> getAllProducts() {
            return makePaymentService.getAllPayments();
    }
}



