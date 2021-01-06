package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Merchant;
import javaproject.BusinessApplication.data.entities.Role;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;
import javaproject.BusinessApplication.exeptions.EntityIsNotMerchantException;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.service.services.MerchantService;
import javaproject.BusinessApplication.service.services.UserService;
import javaproject.BusinessApplication.web.models.MerchantRegisterModel;
import javaproject.BusinessApplication.web.models.MerchantSearchModel;
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
public class MerchantServiceImpl implements MerchantService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @Autowired
    public MerchantServiceImpl(ModelMapper modelMapper, UserService userService,
                                    UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.userService=userService;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void addMerchant(MerchantRegisterModel merchantRegisterModel) {
        Merchant merchant=modelMapper.map(merchantRegisterModel,Merchant.class);
        if (userRepo.existsByUsername(merchant.getUsername())){
            throw new EntityAlreadyExistsException(String.format("User with username '%s' already exist"
                    ,merchant.getUsername()));
        }
        Set<Role> roles=new HashSet<Role>();
        roles.add(new Role("USER"));
        merchant.setAuthorities(roles);
        merchant.setPassword(bCryptPasswordEncoder.encode(merchant.getPassword()));
        userRepo.saveAndFlush(merchant);
    }

    @Override
    public boolean find(MerchantSearchModel merchantSearchModel) {
        return userRepo.existsByUsername(merchantSearchModel.getUsername());
    }

    @Override
    public String getMerchantInfo() {
        List<Merchant> merchants= userRepo.findAll().stream()
                .filter(u->u instanceof Merchant)
                .map(u->(Merchant) u)
                .collect(Collectors.toList());
        return merchants.stream().map(Merchant::toString).collect(Collectors.joining());
    }

    @Override
    @Transactional
    public Merchant delete(MerchantSearchModel merchantSearchModel) {
        validItIsNotAdministrator(merchantSearchModel.getUsername());
        Merchant merchant=(Merchant) userRepo.findByUsername(merchantSearchModel.getUsername())
                .orElseThrow(()->new EntityNotFoundException(String.format
                        ("User with username '%s' does not exist",merchantSearchModel.getUsername())));
        userRepo.deleteByUsername(merchant.getUsername());
        return merchant;
    }

    private void validItIsNotAdministrator(String username){
        if (userService.isAdministrator(username)){
            throw new EntityIsNotMerchantException(String.format("'%s' is administrator",username));
        }
    }
}
