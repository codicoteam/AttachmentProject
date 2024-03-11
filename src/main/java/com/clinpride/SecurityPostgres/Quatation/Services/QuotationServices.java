package com.clinpride.SecurityPostgres.Quatation.Services;

import com.clinpride.SecurityPostgres.Quatation.Model.QuotationModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface QuotationServices {
    Optional<QuotationModel> editQuotation(String id, QuotationModel quotationModel);
    boolean deleteQuotation(String id);
    QuotationModel createQuotation(QuotationModel quotationModel);
    List<QuotationModel> getAllQuotation();
    Optional<QuotationModel> getOneQuotation(String id);
    public List<QuotationModel> getOneByEmail(String email);
    boolean deleteQuotation(List<String> packageIds);
}
