package com.clinpride.SecurityPostgres.MakePayment.Model;

import org.springframework.data.annotation.Id;

public class MakePaymentModel {
    @Id
    private String id;
    private String PaymentTotal;
    private String invitedEmail;
    private String Goods;
    private String invitingUserName;
    private Boolean showInvite=true;
    private Boolean booleanInvite =false;
    private String dateNow;
    private String invitedTime;
}
