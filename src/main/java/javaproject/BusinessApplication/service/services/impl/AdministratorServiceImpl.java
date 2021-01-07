package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Administrator;
import javaproject.BusinessApplication.data.entities.Merchant;
import javaproject.BusinessApplication.data.entities.Product;
import javaproject.BusinessApplication.data.entities.Role;
import javaproject.BusinessApplication.data.repositories.RoleRepository;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.service.services.AdministratorService;
import javaproject.BusinessApplication.util.EmailSender;
import javaproject.BusinessApplication.web.models.AdministratorRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepo;
    private final EmailSender emailSender;

    @Autowired
    public AdministratorServiceImpl(ModelMapper modelMapper, UserRepository userRepo,EmailSender emailSender,
                                    BCryptPasswordEncoder bCryptPasswordEncoder,RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepo=roleRepository;
        this.emailSender=emailSender;
    }


    @Override
    @Transactional
    public void addAdministrator(AdministratorRegisterModel administratorRegisterModel) {
        Administrator administrator=modelMapper.map(administratorRegisterModel,Administrator.class);
        if (userRepo.existsByUsername(administrator.getUsername())){
            throw new EntityAlreadyExistsException(String.format("User with username '%s' already exists"
                    ,administrator.getUsername()));
        }
        administrator.setAuthorities(new HashSet<>());
        administrator.getAuthorities().add(roleRepo.findByAuthority("ADMIN"));
        administrator.setPassword(bCryptPasswordEncoder.encode(administrator.getPassword()));
        userRepo.save(administrator);
    }

    @Override
    public String getAdministratorInfo() {
        List<Administrator> administrators= userRepo.findAll().stream()
                .filter(u->u instanceof Administrator)
                .map(u->(Administrator) u)
                .collect(Collectors.toList());
        return administrators.stream().map(Administrator::toString).collect(Collectors.joining());
    }

    @Override
    public void sendEmailToAdmin(Product product) {
        Administrator administrator=(Administrator) userRepo.findByUsername(product.getAddedBy())
                .orElse(userRepo.findByUsername("admin").orElse(null));
        if (administrator==null){
            return;
        }
        emailSender.send(administrator.getEmail(),product);
    }
}
