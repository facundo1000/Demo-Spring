package com.demospring.usersapp.controllers;

import com.demospring.usersapp.entities.Address;
import com.demospring.usersapp.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses")
public class AddressController {

  @Autowired private AddressService addressService;

  @GetMapping
  public ResponseEntity<List<Address>> findAddressesByProfileIdAndUserId(
      @PathVariable("userId") Integer userId, @PathVariable("profileId") Integer profileId) {
    return new ResponseEntity<>(
            addressService.findAddressesByProfileIdAndUserId(userId, profileId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Address> create(
      @PathVariable("userId") Integer userId,
      @PathVariable("profileId") Integer profileId,
      @RequestBody Address address) {
    return new ResponseEntity<>(addressService.createAddress(userId,profileId,address), HttpStatus.CREATED);
  }
}
