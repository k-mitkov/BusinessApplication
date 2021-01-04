package javaproject.BusinessApplication.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }
}