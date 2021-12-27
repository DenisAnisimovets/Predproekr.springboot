package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleRepositiory;
import web.entity.Role;
import web.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepositiory roleRepositiory;

    public RoleServiceImpl(RoleRepositiory roleDAO) {
        this.roleRepositiory = roleDAO;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepositiory.findAll();
    }

    @Override
    public Role getRoleById(int id) {
        Role role = null;
        Optional<Role> optionalRole = roleRepositiory.findById(id);
        if(optionalRole.isPresent()) {
            role = optionalRole.get();
        }
        return role;
        //return roleRepositiory.findById(id);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepositiory.findByRole(roleName);
    }

    @Override
    public void saveRole(Role role) {
        roleRepositiory.save(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepositiory.save(role);
    }

    @Override
    public void removeRole(int id) {
        roleRepositiory.deleteById(id);
    }

    @Override
    public Set<Role> getSetOfRoles(String[] roleSetString) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roleSetString) {
            roleSet.add(getRoleByName(role));
        }
        return (HashSet) roleSet;
    }

    @Override
    public Set<Role> setRoleByName(String name) {
        Set<Role> roleSet = new HashSet<Role>();
        if (name != null) {
            roleSet.add(getRoleByName(name));
        }
        return roleSet;
        //return roleRepositiory.setRoleByName(name);
    }
}
