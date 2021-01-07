package javaproject.BusinessApplication.web.controllers;

import javaproject.BusinessApplication.service.services.UserService;
import javaproject.BusinessApplication.web.models.EmailModel;
import javaproject.BusinessApplication.web.models.PasswordModel;
import javaproject.BusinessApplication.web.models.SaleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }

    @GetMapping("/account")
    public ModelAndView account() {
        ModelAndView modelAndView = super.view("user/account");
        modelAndView.addObject("isAdministrator",userService.isAdministrator(UserService.getCurrentUsername()));
        return modelAndView;
    }

    @GetMapping("/change/password")
    public ModelAndView changePassword() {
        return new ModelAndView("user/change/password");
    }

    @PostMapping("/change/password")
    public ModelAndView changePasswordConfirm(PasswordModel passwordModel) {
        if (!passwordModel.getPassword().equals(passwordModel.getConfirmPassword())) {
            return super.redirect("/user/change/password");
        }
        userService.changePassword(passwordModel);
        return  super.redirect("/home");
    }

    @GetMapping("/change/email")
    public ModelAndView changeEmail() {
        return new ModelAndView("user/change/email");
    }

    @PostMapping("/change/email")
    public ModelAndView changeEmailConfirm(EmailModel emailModel) {
        if (!emailModel.getEmail().equals(emailModel.getConfirmEmail())) {
            return super.redirect("/user/change/email");
        }
        userService.changeEmail(emailModel);
        return  super.redirect("/home");
    }

    @GetMapping("/delete")
    public ModelAndView delete() {
        return new ModelAndView("user/delete");
    }

    @GetMapping("/deleteAccount")
    public ModelAndView deleteAccount() {
        userService.deleteByUsername(UserService.getCurrentUsername());
        return  super.redirect("/logout");
    }
}