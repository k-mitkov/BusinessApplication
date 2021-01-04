package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Administrator;
import javaproject.BusinessApplication.data.entities.Role;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;
import javaproject.BusinessApplication.service.services.AdministratorService;
import javaproject.BusinessApplication.web.models.AdministratorRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdministratorServiceImpl(ModelMapper modelMapper,
                                    UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void addAdministrator(AdministratorRegisterModel administratorRegisterModel) {
        Administrator administrator=modelMapper.map(administratorRegisterModel,Administrator.class);
        if (userRepo.existsByUsername(administrator.getUsername())){
            throw new EntityAlreadyExistsException(String.format("User with username '%s' already exists"
                    ,administrator.getUsername()));
        }
        Set<Role> roles=new HashSet<Role>();
        roles.add(new Role("ADMIN"));
        administrator.setAuthorities(roles);
        administrator.setPassword(bCryptPasswordEncoder.encode(administrator.getPassword()));
        userRepo.saveAndFlush(administrator);
    }
}
