package com.clinpride.SecurityPostgres.MakePayment.Controller;

import com.clinpride.SecurityPostgres.MakePayment.Model.MakePaymentModel;
import lombok.Data;

@Data

public class MobileRequest {
    MakePaymentModel makePaymentModel;
    String ecoCashNumber;
}
