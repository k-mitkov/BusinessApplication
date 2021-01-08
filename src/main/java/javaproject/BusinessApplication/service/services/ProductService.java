package javaproject.BusinessApplication.service.services;

import javaproject.BusinessApplication.data.entities.Product;
import javaproject.BusinessApplication.web.models.product.ProductAddModel;
import javaproject.BusinessApplication.web.models.product.ProductDeleteModel;
import javaproject.BusinessApplication.web.models.product.ProductUpdatePriceModel;
import javaproject.BusinessApplication.web.models.product.ProductUpdateQuantityModel;

public interface ProductService {

    void addProduct(ProductAddModel productAddModel);
    String getAllProducts();
    Product deleteProduct(ProductDeleteModel productDeleteModel);
    Product updatePrice(ProductUpdatePriceModel productUpdatePriceModel);
    Product updateQuantity(ProductUpdateQuantityModel productUpdateQuantityModel);
    Product updatePriceAndQuantity(ProductAddModel productAddModel);
    Product getProduct(String type,String model);
    void save(Product product);
}
