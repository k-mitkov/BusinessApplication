package javaproject.BusinessApplication.service.services;

import javaproject.BusinessApplication.data.entities.Merchant;
import javaproject.BusinessApplication.web.models.MerchantRegisterModel;
import javaproject.BusinessApplication.web.models.MerchantSearchModel;

public interface MerchantService {

    void addMerchant(MerchantRegisterModel merchantRegisterModel);
    boolean find(MerchantSearchModel merchantSearchModel);
    String getMerchantInfo();
    Merchant delete(MerchantSearchModel merchantSearchModel);
}
