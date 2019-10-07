package ie.kieran.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.kieran.domain.Role;

public interface RoleDao extends JpaRepository<Role, Integer>  {

}
