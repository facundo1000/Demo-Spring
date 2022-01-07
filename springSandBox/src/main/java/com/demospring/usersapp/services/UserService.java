package com.demospring.usersapp.services;

import com.demospring.usersapp.entities.User;
import com.demospring.usersapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

  private static final Logger log = LoggerFactory.getLogger(UserService.class);

  @Autowired private UserRepository userRepository;

  public Page<User> getUsers(int page, int size) {

    return userRepository.findAll(PageRequest.of(page, size));
  }

  public Page<String> getUsernames(int page, int size) {
    return userRepository.findUsernames(PageRequest.of(page, size));
  }

  //@Cacheable("users")
  public User getUserById(Integer userId) {
    log.info("Getting user by userId {}", userId);
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return userRepository
        .findById(userId)
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("User %d not found", userId)));
  }

  //@CacheEvict("users")
  public void deleteUserById(Integer userId){
    User user = getUserById(userId);
    userRepository.delete(user);
  }

  public User getUserByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("User %s not found", username)));
  }

  public User getUserByUsernameAndPassword(String username, String password) {

    return userRepository
        .findByUsernameAndPassword(username, password)
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("User %s do not exist", username)));
  }
}
