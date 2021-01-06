package javaproject.BusinessApplication.service.services;

import javaproject.BusinessApplication.data.entities.Customer;
import javaproject.BusinessApplication.web.models.customer.CustomerAddressModel;
import javaproject.BusinessApplication.web.models.customer.CustomerPhoneModel;
import javaproject.BusinessApplication.web.models.customer.CustomerRegisterModel;
import javaproject.BusinessApplication.web.models.customer.CustomerSearchModel;

public interface CustomerService {

    Customer addCustomer(CustomerRegisterModel customerRegisterModel);
    String getAllCustomers();
    Customer updateAddress(CustomerAddressModel customerAddressModel);
    Customer updatePhoneNumber(CustomerPhoneModel customerPhoneModel);
    Customer updateAddressAndPhoneNumber(CustomerRegisterModel customerRegisterModel);
    Customer deleteCustomer(CustomerSearchModel customerSearchModel);
}
