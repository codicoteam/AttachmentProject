package com.clinpride.SecurityPostgres.Quatation.Services;

import com.clinpride.SecurityPostgres.Quatation.Model.QuotationModel;
import com.clinpride.SecurityPostgres.Quatation.Repository.QuotationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuotationServicesImp implements QuotationServices{

    private final QuotationRepo quotationRepo;
    @Override
    public Optional<QuotationModel> editQuotation(String id, QuotationModel quotationModel) {
        Optional<QuotationModel> productOptional = quotationRepo.findById(id);
        if (productOptional.isPresent()) {
            QuotationModel quotation = productOptional.get();
            quotation.setCustomerName(quotationModel.getCustomerName());
            quotation.setShowQuotation(quotationModel.getShowQuotation());
            quotation.setCustomerEmail(quotationModel.getCustomerEmail());
            quotation.setMessage(quotationModel.getMessage());
            quotation.setTitle(quotationModel.getTitle());
            quotation.setCustomerNumber(quotationModel.getCustomerNumber());
            quotation.setRowMaterials(quotationModel.getTitle());
            quotation.setQuantity(quotationModel.getQuantity());
            quotation.setDoYouHaveMaterials(quotationModel.getDoYouHaveMaterials());
            quotation.setExpectedDeposit(quotationModel.getExpectedDeposit());
            QuotationModel savedProduct = quotationRepo.save(quotation);
            return Optional.of(savedProduct);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteQuotation(String id) {
        Optional<QuotationModel> productOptional = quotationRepo.findById(id);
        if (productOptional.isPresent()) {
            quotationRepo.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public QuotationModel createQuotation(QuotationModel quotationModel) {
        return quotationRepo.save(quotationModel);
    }

    @Override
    public List<QuotationModel> getAllQuotation() {
        return quotationRepo.findAll();
    }
    @Override
    public List<QuotationModel> getOneByEmail(String email) {
        return quotationRepo.findByCustomerEmail(email);
    }

    @Override
    public Optional<QuotationModel> getOneQuotation(String id) {
        return quotationRepo.findById(id);
    }

    @Override
    public boolean deleteQuotation(List<String> packageIds) {
         quotationRepo.deleteByIdIn(packageIds);
        return true;
    }
}
