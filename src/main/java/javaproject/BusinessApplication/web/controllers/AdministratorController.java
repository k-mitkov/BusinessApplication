package javaproject.BusinessApplication.web.controllers;

import javaproject.BusinessApplication.service.services.ProductService;
import javaproject.BusinessApplication.web.models.product.ProductAddModel;
import javaproject.BusinessApplication.web.models.product.ProductDeleteModel;
import javaproject.BusinessApplication.web.models.product.ProductUpdatePriceModel;
import javaproject.BusinessApplication.web.models.product.ProductUpdateQuantityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends BaseController{

    private final ProductService productService;

    @Autowired
    public AdministratorController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView products() {
        return new ModelAndView("administrator/products");
    }

    @GetMapping("/add/product")
    public ModelAndView addProduct() {
        return new ModelAndView("administrator/add/product");
    }

    @PostMapping("/add/product")
    public ModelAndView addProductConfirm(ProductAddModel productAddModel) {
        productService.addProduct(productAddModel);
        return  super.redirect("/home");
    }

    @GetMapping("/update/product")
    public ModelAndView updateProduct() {
        return new ModelAndView("administrator/update/product");
    }

    @GetMapping("/update/product/price")
    public ModelAndView updateProductPrice() {
        return new ModelAndView("administrator/update/product/price");
    }

    @PostMapping("/update/product/price")
    public ModelAndView updateProductPriceConfirm(ProductUpdatePriceModel productUpdatePriceModel) {
        productService.updatePrice(productUpdatePriceModel);
        return  super.redirect("/home");
    }

    @GetMapping("/update/product/quantity")
    public ModelAndView updateProductQuantity() {
        return new ModelAndView("administrator/update/product/quantity");
    }

    @PostMapping("/update/product/quantity")
    public ModelAndView updateProductQuantityConfirm(ProductUpdateQuantityModel productUpdateQuantityModel) {
        productService.updateQuantity(productUpdateQuantityModel);
        return  super.redirect("/home");
    }

    @GetMapping("/update/product/priceAndQuantity")
    public ModelAndView updateProductPriceAndQuantity() {
        return new ModelAndView("administrator/update/product/priceAndQuantity");
    }

    @PostMapping("/update/product/priceAndQuantity")
    public ModelAndView updateProductPriceAndQuantityConfirm(ProductAddModel productAddModel) {
        productService.updatePriceAndQuantity(productAddModel);
        return  super.redirect("/home");
    }

    @GetMapping("/delete/product")
    public ModelAndView deleteProduct() {
        return new ModelAndView("administrator/delete/product");
    }

    @PostMapping("/delete/product")
    public ModelAndView deleteProductConfirm(ProductDeleteModel productDeleteModel) {
        productService.deleteProduct(productDeleteModel);
        return  super.redirect("/home");
    }
}