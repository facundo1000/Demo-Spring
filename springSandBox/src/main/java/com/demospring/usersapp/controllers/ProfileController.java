package com.demospring.usersapp.controllers;

import com.demospring.usersapp.entities.Profile;
import com.demospring.usersapp.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/profiles")
public class ProfileController {

  @Autowired private ProfileService profileService;

  @GetMapping("/{profileId}")
  public ResponseEntity<Profile> getById(
      @PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId) {
    return new ResponseEntity<Profile>(
        profileService.getByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Profile> create(
      @PathVariable("userId") Integer userId, @RequestBody Profile profile) {

    return new ResponseEntity<Profile>(profileService.create(userId, profile), HttpStatus.CREATED);
  }
}
