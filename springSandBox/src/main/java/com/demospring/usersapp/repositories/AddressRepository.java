package com.demospring.usersapp.repositories;

import com.demospring.usersapp.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

  @Query("SELECT a FROM Address a WHERE a.profile.user.id=?1 and a.profile.id=?2 ")
  List<Address> findByProfileId(Integer userId, Integer profileId);
}
