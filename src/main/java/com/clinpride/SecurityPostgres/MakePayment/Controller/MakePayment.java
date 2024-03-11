package com.clinpride.SecurityPostgres.MakePayment.Controller;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePaymentModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.paynow.constants.MobileMoneyMethod;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.core.Payment;
import zw.co.paynow.responses.MobileInitResponse;
import zw.co.paynow.responses.StatusResponse;
import zw.co.paynow.responses.WebInitResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/make-payment")
public class MakePayment {

    @PostMapping("/test-me")
    public void sendPayment(@RequestBody MakePaymentModel makePaymentModel) {
        Paynow paynow = new Paynow("16739", "7d15ff0f-9a44-4767-897f-f6b41a33fcc5");
        Payment payment = paynow.createPayment("Invoice 32", "codicosoftwares@gmail.com");

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
}



