package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Customer;
import javaproject.BusinessApplication.data.entities.Merchant;
import javaproject.BusinessApplication.data.entities.Product;
import javaproject.BusinessApplication.data.entities.Sale;
import javaproject.BusinessApplication.data.repositories.CustomerRepository;
import javaproject.BusinessApplication.data.repositories.ProductRepository;
import javaproject.BusinessApplication.data.repositories.SaleRepository;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.exeptions.NotEnoughProductsExceptions;
import javaproject.BusinessApplication.service.services.AdministratorService;
import javaproject.BusinessApplication.service.services.SaleService;
import javaproject.BusinessApplication.service.services.UserService;
import javaproject.BusinessApplication.web.models.DateModel;
import javaproject.BusinessApplication.web.models.MerchantSearchModel;
import javaproject.BusinessApplication.web.models.SaleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepo;
    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;
    private final AdministratorService administratorService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepo, ProductRepository productRepo,
                           CustomerRepository customerRepo, UserRepository userRepo,
                           AdministratorService administratorService) {
        this.saleRepo = saleRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.administratorService=administratorService;
    }

    @Override
    @Transactional
    public void addSale(SaleModel saleModel) {
        Product product=productRepo.findByTypeAndModel(saleModel.getProductType(),saleModel.getProductModel())
                .orElseThrow(()->new EntityNotFoundException(String.format("Not found product with type '%s' and model '%s'"
                        ,saleModel.getProductType(),saleModel.getProductModel())));
        Customer customer=customerRepo.findByName(saleModel.getCustomerName())
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("Not found customer with type '%s'",saleModel.getCustomerName())));
        Merchant merchant=(Merchant) userRepo.findByUsername(UserService.getCurrentUsername())
                .orElseThrow(()->new EntityNotFoundException(String.format
                        ("User with username '%s' does not exist",UserService.getCurrentUsername())));
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

        productRepo.save(product);
        customerRepo.save(customer);
        userRepo.save(merchant);
        saleRepo.save(sale);
    }

    @Override
    public String getSaleReport(MerchantSearchModel merchantSearchModel) {
        List<Sale> sales=saleRepo.findBySellerName(merchantSearchModel.getUsername());
        sales.sort(Comparator.comparing(Sale::getDate));
        return sales.stream().map(Sale::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String getSaleReport(DateModel dateModel) {
        String[] input=dateModel.getDateFrom().split("-");
        Date dateFrom=new Date(Integer.parseInt(input[0])-1900,Integer.parseInt(input[1])-1, Integer.parseInt(input[2]));
        input=dateModel.getDateTo().split("-");
        Date dateTo=new Date(Integer.parseInt(input[0])-1900,Integer.parseInt(input[1])-1, Integer.parseInt(input[2]));
        List<Sale> sales=saleRepo.findByDateBetween(dateFrom,dateTo);
        sales.sort(Comparator.comparing(Sale::getDate));
        return sales.stream().map(Sale::toString).collect(Collectors.joining());
    }
}
