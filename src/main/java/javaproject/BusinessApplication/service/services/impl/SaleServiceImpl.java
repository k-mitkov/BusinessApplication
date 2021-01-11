package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Customer;
import javaproject.BusinessApplication.data.entities.Merchant;
import javaproject.BusinessApplication.data.entities.Product;
import javaproject.BusinessApplication.data.entities.Sale;
import javaproject.BusinessApplication.data.repositories.SaleRepository;
import javaproject.BusinessApplication.exeptions.DateException;
import javaproject.BusinessApplication.exeptions.NotEnoughProductsExceptions;
import javaproject.BusinessApplication.service.services.*;
import javaproject.BusinessApplication.web.models.DateModel;
import javaproject.BusinessApplication.web.models.MerchantSearchModel;
import javaproject.BusinessApplication.web.models.SaleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepo;
    private final ProductService productService;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final AdministratorService administratorService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepo, ProductService productService,
                           CustomerService customerService, AdministratorService administratorService,
                           MerchantService merchantService) {
        this.saleRepo = saleRepo;
        this.productService = productService;
        this.customerService = customerService;
        this.administratorService=administratorService;
        this.merchantService=merchantService;
    }

    @Override
    @Transactional
    public void addSale(SaleModel saleModel) {
        Product product=productService.getProduct(saleModel.getProductType(),saleModel.getProductModel());
        Customer customer=customerService.getCustomer(saleModel.getCustomerName());
        Merchant merchant= merchantService.getMerchant(UserService.getCurrentUsername());
        productService.validateQuantity(saleModel.getQuantity());
        if (product.getQuantity()<saleModel.getQuantity()){
            throw  new NotEnoughProductsExceptions(
                    String.format("You cannot sell %d products because there are only %d quantities of this product."
                            ,saleModel.getQuantity(),product.getQuantity()));
        }
        Sale sale=new Sale(product.getType()+" "+product.getModel(),
                customer.getName(),merchant.getUsername(),saleModel.getQuantity(),product.getPrice());
        product.setQuantity(product.getQuantity()-saleModel.getQuantity());
        if (product.getQuantity()<50){
            administratorService.sendEmailToAdmin(product);
        }
        customer.setMoneySpend(customer.getMoneySpend().add(sale.getTotalPrice()));
        merchant.setSalesValue(merchant.getSalesValue().add(sale.getTotalPrice()));

        productService.save(product);
        customerService.save(customer);
        merchantService.save(merchant);
        saleRepo.save(sale);
    }

    @Override
    public String getSaleReport(MerchantSearchModel merchantSearchModel) {
        List<Sale> sales=saleRepo.findBySellerName(merchantSearchModel.getUsername());
        sales.sort(Comparator.comparing(Sale::getDate).reversed());
        return sales.stream().map(Sale::toString).collect(Collectors.joining());
    }

    @Override
    public String getSaleReport(DateModel dateModel) {
        List<Integer> input= Arrays.stream(dateModel.getDateFrom().split("-"))
                .map(Integer::parseInt).collect(Collectors.toList());
        LocalDate dateFrom=LocalDate.of(input.get(0),input.get(1),input.get(2));
        input=Arrays.stream(dateModel.getDateTo().split("-"))
                .map(Integer::parseInt).collect(Collectors.toList());
        LocalDate dateTo=LocalDate.of(input.get(0),input.get(1),input.get(2));
        validateDates(dateFrom,dateTo);
        List<Sale> sales=saleRepo.findByDateBetween(dateFrom,dateTo);
        sales.sort(Comparator.comparing(Sale::getDate).reversed());
        return sales.stream().map(Sale::toString).collect(Collectors.joining());
    }

    private void validateDates(LocalDate dateFrom,LocalDate dateTo){
        if (dateFrom.compareTo(dateTo)>0){
            throw new DateException("Date from must be before date to!");
        }
    }
}
