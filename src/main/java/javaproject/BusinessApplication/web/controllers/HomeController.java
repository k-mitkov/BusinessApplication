package javaproject.BusinessApplication.web.controllers;

import javaproject.BusinessApplication.service.services.UserService;
import javaproject.BusinessApplication.service.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }


    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = super.view("home");
        modelAndView.addObject("isAdministrator",  userService.isAdministrator(UserService.getCurrentUsername()));
        return modelAndView;
    }
}