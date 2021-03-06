package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Product;
import javaproject.BusinessApplication.data.repositories.ProductRepository;
import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.exeptions.NegativePriceException;
import javaproject.BusinessApplication.exeptions.NegativeQuantityException;
import javaproject.BusinessApplication.service.services.ProductService;
import javaproject.BusinessApplication.service.services.UserService;
import javaproject.BusinessApplication.web.models.product.ProductAddModel;
import javaproject.BusinessApplication.web.models.product.ProductDeleteModel;
import javaproject.BusinessApplication.web.models.product.ProductUpdatePriceModel;
import javaproject.BusinessApplication.web.models.product.ProductUpdateQuantityModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductAddModel productAddModel) {
        validateQuantity(productAddModel.getQuantity());
        validatePrice(productAddModel.getPrice());
        Product product=modelMapper.map(productAddModel,Product.class);
        if (productRepo.existsByTypeAndModel(product.getType(),product.getModel())){
            throw new EntityAlreadyExistsException(String.format("Product with type '%s' and model '%s' already exists"
                    ,product.getType(),product.getModel()));
        }
        product.setAddedBy(UserService.getCurrentUsername());
        this.save(product);
    }

    @Override
    public String getAllProducts() {
        return productRepo.findAll().stream().sorted(Comparator.comparing(Product::getQuantity).reversed())
                .map(Product::toString).collect(Collectors.joining());
    }

    @Override
    @Transactional
    public Product deleteProduct(ProductDeleteModel productDeleteModel) {
        Product product=this.getProduct(productDeleteModel.getType(),productDeleteModel.getModel());
        productRepo.deleteByTypeAndModel(productDeleteModel.getType(),productDeleteModel.getModel());
        return product;
    }

    @Override
    public Product updatePrice(ProductUpdatePriceModel productUpdatePriceModel){
        validatePrice(productUpdatePriceModel.getPrice());
        Product product=this.getProduct(productUpdatePriceModel.getType(),productUpdatePriceModel.getModel());
        product.setPrice(productUpdatePriceModel.getPrice());
        this.save(product);
        return product;
    }

    @Override
    public Product updateQuantity(ProductUpdateQuantityModel productUpdateQuantityModel){
        validateQuantity(productUpdateQuantityModel.getQuantity());
        Product product=this.getProduct(productUpdateQuantityModel.getType(),productUpdateQuantityModel.getModel());
        product.setQuantity(product.getQuantity()+productUpdateQuantityModel.getQuantity());
        this.save(product);
        return product;
    }

    @Override
    public Product updatePriceAndQuantity(ProductAddModel productAddModel) {
        validateQuantity(productAddModel.getQuantity());
        validatePrice(productAddModel.getPrice());
        Product product=this.getProduct(productAddModel.getType(),productAddModel.getModel());
        product.setPrice(productAddModel.getPrice());
        product.setQuantity(product.getQuantity()+productAddModel.getQuantity());
        this.save(product);
        return product;
    }

    @Override
    public Product getProduct(String type,String model){
        return productRepo.findByTypeAndModel(type,model)
                .orElseThrow(()->new EntityNotFoundException
                        (String.format("Not found product with type '%s' and model '%s'",type,model)));
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepo.save(product);
    }

    private void validatePrice(BigDecimal price){
        if (price.compareTo(BigDecimal.ZERO)<=0){
            throw new NegativePriceException(String
                    .format("Price cannot be a negative or zero!    Input:%s",price));
        }
    }
    public void validateQuantity(int quantity){
        if (quantity<=0){
            throw new NegativeQuantityException(String
                    .format("Quantity cannot be a negative or zero!    Input:%s",quantity));
        }
    }
}
