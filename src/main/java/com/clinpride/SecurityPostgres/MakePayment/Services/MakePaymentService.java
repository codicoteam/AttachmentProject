package com.clinpride.SecurityPostgres.MakePayment.Services;


import com.clinpride.SecurityPostgres.MakePayment.Model.MakePaymentModel;

import java.util.List;

public interface MakePaymentService {
    String createPayment(MakePaymentModel makePayment);
    String createEcoCashPayment(MakePaymentModel makePayment);
    List<MakePaymentModel> getAllPayments();
    String deleteManyByIds(List<String> makePayments);
}
