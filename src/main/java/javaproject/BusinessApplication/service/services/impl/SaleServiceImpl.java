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
import javaproject.BusinessApplication.service.services.SaleService;
import javaproject.BusinessApplication.web.models.SaleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepo;
    private final ProductRepository productRepo;
    private final CustomerRepository customerRepo;
    private final UserRepository userRepo;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepo, ProductRepository productRepo
            , CustomerRepository customerRepo, UserRepository userRepo) {
        this.saleRepo = saleRepo;
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
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
        Merchant merchant=(Merchant) userRepo.findByUsername(UserServiceImpl.loggedUsername)
                .orElseThrow(()->new EntityNotFoundException(String.format
                        ("User with username '%s' does not exist",UserServiceImpl.loggedUsername)));
        if (product.getQuantity()<saleModel.getQuantity()){
            throw  new NotEnoughProductsExceptions(
                    String.format("You cannot sell %d products because there are only %d quantities of this product."
                            ,saleModel.getQuantity(),product.getQuantity()));
        }
        Sale sale=new Sale(product.getType()+" "+product.getModel(),
                customer.getName(),merchant.getUsername(),saleModel.getQuantity(),product.getPrice());
        product.setQuantity(product.getQuantity()-saleModel.getQuantity());
        if (product.getQuantity()<20){
            //TODO
        }
        customer.setMoneySpend(customer.getMoneySpend().add(sale.getTotalPrice()));
        merchant.setSalesValue(merchant.getSalesValue().add(sale.getTotalPrice()));

        productRepo.save(product);
        customerRepo.save(customer);
        userRepo.save(merchant);
        saleRepo.save(sale);
    }
}
