package com.demospring.usersapp;

import com.demospring.usersapp.entities.Role;
import com.demospring.usersapp.entities.User;
import com.demospring.usersapp.entities.UserInRole;
import com.demospring.usersapp.repositories.RoleRepository;
import com.demospring.usersapp.repositories.UserInRoleRepository;
import com.demospring.usersapp.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class UsersAppApplication implements ApplicationRunner {

  @Autowired private Faker faker;

  @Autowired private UserRepository repository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private UserInRoleRepository userInRoleRepository;

  private static final Logger log = LoggerFactory.getLogger(UsersAppApplication.class);

  public static void main(String[] args) {

    SpringApplication.run(UsersAppApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Role roles[] = {new Role("ADMIN"), new Role("USER"), new Role("SUPPORT")};

    for (Role rol : roles) {
      roleRepository.save(rol);
    }

    for (int i = 0; i < 15; i++) {
      User user = new User();

      user.setUsername(faker.artist().name());
      user.setPassword(faker.numerify(faker.number().digits(5)));
      User userCreated = repository.save(user);
      UserInRole userInRole = new UserInRole(userCreated, roles[new Random().nextInt(3)]);
      log.info(
          "User created username {} password {} role {}",
          userCreated.getUsername(),
          userCreated.getPassword(),
          userInRole.getRole().getName());
      userInRoleRepository.save(userInRole);
    }
  }
}
