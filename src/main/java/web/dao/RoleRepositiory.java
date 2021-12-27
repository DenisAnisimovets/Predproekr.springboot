package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.entity.Role;


@Repository
public interface RoleRepositiory extends JpaRepository <Role, Integer> {
    public Role findByRole(String role);
}