package com.demospring.usersapp.repositories;

import com.demospring.usersapp.entities.User;
import com.demospring.usersapp.entities.UserInRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInRoleRepository extends CrudRepository<UserInRole, Integer> {

    @Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
    public List<User> findUsersByRoleName(String roleName);

    public List<UserInRole> findByUser(User user);
}
