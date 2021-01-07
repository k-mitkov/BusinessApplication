package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Administrator;
import javaproject.BusinessApplication.data.entities.User;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.service.models.UserServiceModel;
import javaproject.BusinessApplication.service.services.UserService;
import javaproject.BusinessApplication.web.models.EmailModel;
import javaproject.BusinessApplication.web.models.PasswordModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo,
                           ModelMapper modelMapper,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
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

    @Override
    public void changePassword(PasswordModel passwordModel) {
        User user=userRepo.findByUsername(UserService.getCurrentUsername())
                .orElseThrow(()->new EntityNotFoundException(String.format("There is no user with username:%s",
                        UserService.getCurrentUsername())));
        user.setPassword(bCryptPasswordEncoder.encode(passwordModel.getPassword()));
        userRepo.saveAndFlush(user);
    }

    @Override
    public void changeEmail(EmailModel emailModel) {
        Administrator administrator=(Administrator) userRepo.findByUsername(UserService.getCurrentUsername())
                .orElseThrow(()->new EntityNotFoundException(String.format("There is no user with username:%s",
                        UserService.getCurrentUsername())));
        administrator.setEmail(emailModel.getEmail());
        userRepo.saveAndFlush(administrator);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        userRepo.deleteByUsername(username);
    }
}
