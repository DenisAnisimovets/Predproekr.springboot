package web.service;

import web.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

        List<Role> getAllRoles();

        Role getRoleById(int id);

        Role getRoleByName(String roleName);

        void saveRole(Role role);

        void updateRole(Role role);

        void removeRole(int id);

        Set<Role> getSetOfRoles(String[] roleSet);

        Set<Role> setRoleByName(String name);

}
