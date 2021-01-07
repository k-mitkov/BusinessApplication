package javaproject.BusinessApplication.init;

import javaproject.BusinessApplication.data.repositories.UserRepository;
import javaproject.BusinessApplication.service.services.AdministratorService;
import javaproject.BusinessApplication.service.services.RoleService;
import javaproject.BusinessApplication.web.models.AdministratorRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {

    private final AdministratorService administratorService;
    private final UserRepository userRepository;
    private final RoleService roleService;


    @Autowired
    public AppInitializer(AdministratorService administratorService,
                          UserRepository userRepository,RoleService roleService) {
        this.administratorService = administratorService;
        this.userRepository = userRepository;
        this.roleService=roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count()==0){
            roleService.seedRolesInDb();
            administratorService.addAdministrator(new AdministratorRegisterModel("admin","Pesho"
                    ,"Peshev","admin","admin","admin@gmail.com"));
        }
    }
}
