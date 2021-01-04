package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Administrator;
import javaproject.BusinessApplication.data.entities.User;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.service.models.UserServiceModel;
import javaproject.BusinessApplication.service.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public static String loggedUsername;

    private UserRepository userRepo;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        loggedUsername=s;
        return userRepo.findByUsername(s)
                .orElseThrow(()->new EntityNotFoundException(String.format("There is no user with username:%s",s)));
    }

    @Override
    public UserServiceModel getUser(String username) {
        User user=userRepo.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException(String.format("There is no user with username:%s",username)));
        return modelMapper.map(user,UserServiceModel.class);
    }

    @Override
    public boolean isAdministrator(String username) {
        User user=userRepo.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException(String.format("There is no user with username:%s",username)));
        return user instanceof Administrator;
    }
}
