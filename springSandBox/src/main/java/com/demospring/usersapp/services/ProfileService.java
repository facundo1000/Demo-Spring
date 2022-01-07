package com.demospring.usersapp.services;

import com.demospring.usersapp.entities.Profile;
import com.demospring.usersapp.entities.User;
import com.demospring.usersapp.repositories.ProfileRepository;
import com.demospring.usersapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProfileService {

  @Autowired private ProfileRepository profileRepository;

  @Autowired private UserRepository userRepository;

  public Profile create(Integer userId, Profile profile) {

    Optional<User> result = userRepository.findById(userId);
    if (result.isPresent()) {
      profile.setUser(result.get());
      return profileRepository.save(profile);
    } else {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
    }
  }


  public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
    return profileRepository
        .findByUserIdAndProfileId(userId, profileId)
        .orElseThrow(
            () ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format(
                        "Profile not found for user %d and profile %d", userId, profileId)));
  }
}
