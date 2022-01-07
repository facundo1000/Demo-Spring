package com.demospring.usersapp.config;

import com.demospring.usersapp.entities.User;
import com.demospring.usersapp.entities.UserInRole;
import com.demospring.usersapp.repositories.UserInRoleRepository;
import com.demospring.usersapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoUserDetailsService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Autowired private UserInRoleRepository userInRoleRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optional = userRepository.findByUsername(username);
    if (optional.isPresent()) {
      User user = optional.get();
      List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
      String[] roles = userInRoles.stream().map(r -> r.getRole().getName()).toArray(String[]::new);

      return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
          .password(passwordEncoder.encode(user.getPassword()))
          .roles(roles)
          .build();
    } else {
      throw new UsernameNotFoundException("Username " + username + " not found");
    }
  }
}
