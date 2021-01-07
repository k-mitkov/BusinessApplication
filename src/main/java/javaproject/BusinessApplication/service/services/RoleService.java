package javaproject.BusinessApplication.service.services;


import javaproject.BusinessApplication.service.models.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String role);
}