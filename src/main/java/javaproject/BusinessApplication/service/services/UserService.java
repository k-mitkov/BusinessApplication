package javaproject.BusinessApplication.service.services;

import javaproject.BusinessApplication.service.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserServiceModel getUser(String username);
    boolean isAdministrator(String username);
}
