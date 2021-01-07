package javaproject.BusinessApplication.web.controllers;

import javaproject.BusinessApplication.service.services.CustomerService;
import javaproject.BusinessApplication.service.services.MerchantService;
import javaproject.BusinessApplication.service.services.SaleService;
import javaproject.BusinessApplication.web.models.SaleModel;
import javaproject.BusinessApplication.web.models.TweetModel;
import javaproject.BusinessApplication.web.models.customer.CustomerAddressModel;
import javaproject.BusinessApplication.web.models.customer.CustomerPhoneModel;
import javaproject.BusinessApplication.web.models.customer.CustomerRegisterModel;
import javaproject.BusinessApplication.web.models.customer.CustomerSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController{

    private final CustomerService customerService;
    private final SaleService saleService;
    private final MerchantService merchantService;

    @Autowired
    public MerchantController(CustomerService customerService, SaleService saleService,
                              MerchantService merchantService) {
        this.customerService = customerService;
        this.saleService = saleService;
        this.merchantService=merchantService;
    }

    @GetMapping("/customers")
    public ModelAndView customers() {
        return new ModelAndView("merchant/customers");
    }


    @GetMapping("/add/customer")
    public ModelAndView addCustomer() {
        return new ModelAndView("merchant/add/customer");
    }

    @PostMapping("/add/customer")
    public ModelAndView addCustomerConfirm(CustomerRegisterModel customerRegisterModel) {
        customerService.addCustomer(customerRegisterModel);
        return  super.redirect("/home");
    }

    @GetMapping("/view/customers")
    public ModelAndView viewAllCustomers() {
        return new ModelAndView("merchant/view/customers","customers",customerService.getAllCustomers());
    }

    @GetMapping("/update/customer")
    public ModelAndView updateCustomer() {
        return new ModelAndView("merchant/update/customer");
    }

    @GetMapping("/update/customer/address")
    public ModelAndView updateCustomerAddress() {
        return new ModelAndView("merchant/update/customer/address");
    }

    @PostMapping("/update/customer/address")
    public ModelAndView updateCustomerAddressConfirm(CustomerAddressModel customerAddressModel) {
        customerService.updateAddress(customerAddressModel);
        return  super.redirect("/home");
    }

    @GetMapping("/update/customer/phoneNumber")
    public ModelAndView updateCustomerPhoneNumber() {
        return new ModelAndView("merchant/update/customer/phoneNumber");
    }

    @PostMapping("/update/customer/phoneNumber")
    public ModelAndView updateCustomerPhoneNumberConfirm(CustomerPhoneModel customerPhoneModel) {
        customerService.updatePhoneNumber(customerPhoneModel);
        return  super.redirect("/home");
    }

    @GetMapping("/update/customer/addressAndPhoneNumber")
    public ModelAndView updateCustomerAddressAndPhoneNumber() {
        return new ModelAndView("merchant/update/customer/addressAndPhoneNumber");
    }

    @PostMapping("/update/customer/addressAndPhoneNumber")
    public ModelAndView updateCustomerAddressAndPhoneNumberConfirm(CustomerRegisterModel customerRegisterModel) {
        customerService.updateAddressAndPhoneNumber(customerRegisterModel);
        return  super.redirect("/home");
    }

    @GetMapping("/delete/customer")
    public ModelAndView deleteCustomer() {
        return new ModelAndView("merchant/delete/customer");
    }

    @PostMapping("/delete/customer")
    public ModelAndView deleteCustomerConfirm(CustomerSearchModel customerSearchModel) {
        customerService.deleteCustomer(customerSearchModel);
        return  super.redirect("/home");
    }

    @GetMapping("/sale")
    public ModelAndView sale() {
        return new ModelAndView("merchant/sale");
    }

    @PostMapping("/sale")
    public ModelAndView saleConfirm(SaleModel saleModel) {
        saleService.addSale(saleModel);
        return  super.redirect("/home");
    }

    @GetMapping("/tweet")
    public ModelAndView tweet() {
        return new ModelAndView("merchant/tweet");
    }

    @PostMapping("/tweet")
    public ModelAndView tweetConfirm(TweetModel tweetModel) {
        merchantService.tweet(tweetModel);
        return  super.redirect("/home");
    }
}
