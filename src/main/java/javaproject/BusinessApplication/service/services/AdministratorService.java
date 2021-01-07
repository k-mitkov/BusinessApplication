package javaproject.BusinessApplication.service.services;

import javaproject.BusinessApplication.data.entities.Product;
import javaproject.BusinessApplication.web.models.AdministratorRegisterModel;

public interface AdministratorService {
    void addAdministrator(AdministratorRegisterModel administratorRegisterModel);
    String getAdministratorInfo();
    void sendEmailToAdmin(Product product);
}
