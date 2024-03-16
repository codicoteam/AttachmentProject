package com.clinpride.SecurityPostgres.MakePayment.Services.impleServices;

import com.clinpride.SecurityPostgres.BulkProducts.Models.BulkBuying;
import com.clinpride.SecurityPostgres.InviteUser.repository.InviteUserRepo;
import com.clinpride.SecurityPostgres.MakePayment.Model.MakePaymentModel;
import com.clinpride.SecurityPostgres.MakePayment.Repository.MakePaymentRepo;
import com.clinpride.SecurityPostgres.MakePayment.Services.MakePaymentService;
import com.clinpride.SecurityPostgres.Products.models.ProductsModel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import zw.co.paynow.constants.MobileMoneyMethod;
import zw.co.paynow.core.Payment;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.responses.MobileInitResponse;
import zw.co.paynow.responses.StatusResponse;
import zw.co.paynow.responses.WebInitResponse;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class implementsServices implements MakePaymentService {
    private final JavaMailSender javaMailSender;
    private final MakePaymentRepo makePaymentRepo;
    @Override
    public String createPayment(MakePaymentModel makePayment) {
        double totalPrice = 0.0;

        Paynow paynow = new Paynow("16739", "7d15ff0f-9a44-4767-897f-f6b41a33fcc5");
        Payment payment = paynow.createPayment(makePayment.getInvoiceNumber(), "codicosoftwares@gmail.com");
        // Add items to the payment
        for (MakePaymentModel.PriceProductsAndNumber product : makePayment.getProducts()) {
            totalPrice += product.getPrice();
        }
        payment.add("Furniture", totalPrice);

        WebInitResponse response = paynow.send(payment);
        //If a mobile transaction,
        //MobileInitResponse response = paynow.sendMobile(payment, "0771234567", MobileMoneyMethod.ECOCASH);
        if (response.isRequestSuccess()) {
            // Get the url to redirect the user to they can make payment
            String redirectURL = response.redirectURL();
            System.out.println(redirectURL);
            // Get the poll url of the transaction
            String pollUrl = response.pollUrl();
            StatusResponse status = paynow.pollTransaction(pollUrl);
            System.out.println("pollurl: "+pollUrl);
            SimpleMailMessage message = getSimpleMailMessage(makePayment);
            javaMailSender.send(message);
            makePayment.setPollUrl(pollUrl);
            makePayment.setPaymentMethod("Web Based");
            makePaymentRepo.save(makePayment);
            System.out.println(makePayment);
            return redirectURL;
        } else {
            System.out.println(response.errors());
            return response.errors();
        }
    }
    @Override
    public String createEcoCashPayment(MakePaymentModel makePayment) {
        System.out.println(makePayment.getInvoiceNumber());
        double totalPrice = 0.0;
        Paynow paynow = new Paynow("16739", "7d15ff0f-9a44-4767-897f-f6b41a33fcc5");
        Payment payment = paynow.createPayment(makePayment.getInvoiceNumber(), "codicosoftwares@gmail.com");
        // Add items to the payment
        for (MakePaymentModel.PriceProductsAndNumber product : makePayment.getProducts()) {
            totalPrice += product.getPrice();
        }
        payment.add("Furniture", totalPrice);
        MobileInitResponse response = paynow.sendMobile(payment, makePayment.getCustomerPhoneNumber(), MobileMoneyMethod.ECOCASH);
        if (response.isRequestSuccess()) {
            String pollUrl = response.pollUrl();
            System.out.println("pollurl: "+pollUrl);
            SimpleMailMessage message = getSimpleMailMessage(makePayment);
            javaMailSender.send(message);
            makePayment.setPollUrl(pollUrl);
            makePayment.setPaymentMethod("Ecocash");
            makePaymentRepo.save(makePayment);
            System.out.println(makePayment);
            return "Transaction was successful enter pin on your mobile phone";
        } else {
            return response.errors();
        }
    }

    private static SimpleMailMessage getSimpleMailMessage(MakePaymentModel makePayment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(makePayment.getCustomerEmail());
        message.setSubject("Reminder to finish checkout");
        message.setText("Dear User,\n\n" +
                "This Email has been send to you by MapMak Furnitures,\n\n"+
                "To alert you to finish checking out buying your furnitures through the MapMak System" +
                "Have a great day!");
        return message;
    }


    @Override
    public List<MakePaymentModel> getAllPayments() {
            return makePaymentRepo.findAll();
    }

    @Override
    public String deleteManyByIds(List<String> makePayments) {
        makePaymentRepo.deleteByIdIn(makePayments);
        return "Deleted Successfully";
    }
}
