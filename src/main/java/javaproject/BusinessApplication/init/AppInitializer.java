package javaproject.BusinessApplication.init;

import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.service.services.AdministratorService;
import javaproject.BusinessApplication.web.models.AdministratorRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {

    private AdministratorService administratorService;
    private UserRepository userRepository;

    @Autowired
    public AppInitializer(AdministratorService administratorService, UserRepository userRepository) {
        this.administratorService = administratorService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count()==0){
            administratorService.addAdministrator(new AdministratorRegisterModel("admin","Pesho"
                    ,"Peshev","admin","admin","admin@gmail.com"));
        }
    }
}
