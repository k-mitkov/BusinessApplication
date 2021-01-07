package javaproject.BusinessApplication.service.services;

import javaproject.BusinessApplication.web.models.DateModel;
import javaproject.BusinessApplication.web.models.MerchantSearchModel;
import javaproject.BusinessApplication.web.models.SaleModel;

public interface SaleService {

    void addSale(SaleModel saleModel);
    String getSaleReport(MerchantSearchModel merchantSearchModel);
    String getSaleReport(DateModel dateModel);
}
