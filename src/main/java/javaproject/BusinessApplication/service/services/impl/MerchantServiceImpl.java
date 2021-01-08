package javaproject.BusinessApplication.service.services.impl;

import javaproject.BusinessApplication.data.entities.Merchant;
import javaproject.BusinessApplication.data.repositories.RoleRepository;
import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;
import javaproject.BusinessApplication.exeptions.EntityIsNotMerchantException;
import javaproject.BusinessApplication.exeptions.EntityNotFoundException;
import javaproject.BusinessApplication.service.services.MerchantService;
import javaproject.BusinessApplication.service.services.UserService;
import javaproject.BusinessApplication.util.TweetSender;
import javaproject.BusinessApplication.web.models.MerchantRegisterModel;
import javaproject.BusinessApplication.web.models.MerchantSearchModel;
import javaproject.BusinessApplication.web.models.TweetModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final RoleRepository roleRepo;
    private final TweetSender tweetSender;

    @Autowired
    public MerchantServiceImpl(ModelMapper modelMapper, UserService userService,RoleRepository roleRepo,
                               UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
                               TweetSender tweetSender) {
        this.modelMapper = modelMapper;
        this.userService=userService;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepo=roleRepo;
        this.tweetSender=tweetSender;
    }

    @Override
    @Transactional
    public void addMerchant(MerchantRegisterModel merchantRegisterModel) {
        Merchant merchant=modelMapper.map(merchantRegisterModel,Merchant.class);
        if (userRepo.existsByUsername(merchant.getUsername())){
            throw new EntityAlreadyExistsException(String.format("User with username '%s' already exist"
                    ,merchant.getUsername()));
        }
        merchant.setAuthorities(new HashSet<>());
        merchant.getAuthorities().add(roleRepo.findByAuthority("USER"));
        merchant.setPassword(bCryptPasswordEncoder.encode(merchant.getPassword()));
        userRepo.save(merchant);
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
        Merchant merchant=this.getMerchant(merchantSearchModel.getUsername());
        userRepo.deleteByUsername(merchant.getUsername());
        return merchant;
    }

    @Override
    public Merchant getMerchant(String username){
        validItIsNotAdministrator(username);
        return (Merchant) userService.getUser(username);
    }

    @Override
    public void save(Merchant merchant) {
        userRepo.save(merchant);
    }

    @Override
    public void tweet(TweetModel tweetModel) {
        tweetSender.tweet(tweetModel.getTweetMassage());
    }

    private void validItIsNotAdministrator(String username){
        if (userService.isAdministrator(username)){
            throw new EntityIsNotMerchantException(String.format("'%s' is administrator",username));
        }
    }
}
