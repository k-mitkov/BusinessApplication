package javaproject.BusinessApplication.web.controllers;

import javaproject.BusinessApplication.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class ViewController extends BaseController{
    private final ProductService productService;

    @Autowired
    public ViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ModelAndView viewAllProducts() {
        return new ModelAndView("view/products","products",productService.getAllProducts());
    }
}
