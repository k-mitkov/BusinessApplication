package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Product;
import javaproject.BusinessApplication.data.repositories.ProductRepository;
import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
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
        Product product=modelMapper.map(productAddModel,Product.class);
        if (productRepo.existsByTypeAndModel(product.getType(),product.getModel())){
            throw new EntityAlreadyExistsException(String.format("Product with type '%s' and model '%s' already exists"
                    ,product.getType(),product.getModel()));
        }
        product.setAddedBy(UserService.getCurrentUsername());
        productRepo.saveAndFlush(product);
    }

    @Override
    public String getAllProducts() {
        StringBuilder sb = new StringBuilder();
        productRepo.findAll().forEach(prod ->
                sb.append("Product: ").append(prod.getType())
                        .append("\n     Model: ").append(prod.getModel())
                        .append("\n     Quantity: ").append(prod.getQuantity())
                        .append("\n     Price: ").append(prod.getPrice())
                        .append("\n")
        );
        return sb.toString();
    }

    @Override
    @Transactional
    public Product deleteProduct(ProductDeleteModel productDeleteModel) {
        Product product=productRepo.findByTypeAndModel(productDeleteModel.getType(),productDeleteModel.getModel())
                .orElseThrow(()->new EntityNotFoundException(String.format("Not found product with type '%s' and model '%s'"
                ,productDeleteModel.getType(),productDeleteModel.getModel())));
        productRepo.deleteByTypeAndModel(productDeleteModel.getType(),productDeleteModel.getModel());
        return product;
    }

    @Override
    public Product updatePrice(ProductUpdatePriceModel productUpdatePriceModel){
        Product product=productRepo.findByTypeAndModel(productUpdatePriceModel.getType(),productUpdatePriceModel.getModel())
                .orElseThrow(()->new EntityNotFoundException(String.format("Not found product with type '%s' and model '%s'"
                        ,productUpdatePriceModel.getType(),productUpdatePriceModel.getModel())));
        product.setPrice(productUpdatePriceModel.getPrice());
        productRepo.saveAndFlush(product);
        return product;
    }

    @Override
    public Product updateQuantity(ProductUpdateQuantityModel productUpdateQuantityModel){
        Product product=productRepo.findByTypeAndModel(productUpdateQuantityModel.getType(),productUpdateQuantityModel.getModel())
                .orElseThrow(()->new EntityNotFoundException(String.format("Not found product with type '%s' and model '%s'"
                        ,productUpdateQuantityModel.getType(),productUpdateQuantityModel.getModel())));
        product.setQuantity(product.getQuantity()+productUpdateQuantityModel.getQuantity());
        productRepo.saveAndFlush(product);
        return product;
    }

    @Override
    public Product updatePriceAndQuantity(ProductAddModel productAddModel) {
        Product product=productRepo.findByTypeAndModel(productAddModel.getType(),productAddModel.getModel())
                .orElseThrow(()->new EntityNotFoundException(String.format("Not found product with type '%s' and model '%s'"
                        ,productAddModel.getType(),productAddModel.getModel())));
        product.setPrice(productAddModel.getPrice());
        product.setQuantity(product.getQuantity()+productAddModel.getQuantity());
        productRepo.saveAndFlush(product);
        return product;
    }
}
