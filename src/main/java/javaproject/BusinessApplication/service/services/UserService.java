package javaproject.BusinessApplication.service.services;

import javaproject.BusinessApplication.data.entities.User;
import javaproject.BusinessApplication.web.models.EmailModel;
import javaproject.BusinessApplication.web.models.PasswordModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUser(String username);
    boolean isAdministrator(String username);
    void changePassword(PasswordModel passwordModel);
    void changeEmail(EmailModel emailModel);
    void deleteByUsername(String username);
    static String getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
