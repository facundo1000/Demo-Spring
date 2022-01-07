package com.demospring.usersapp.repositories;

import com.demospring.usersapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  public Optional<User> findByUsername(String username);

  public Optional<User> findByUsernameAndPassword(String username, String password);

  /**
   * Esto NO es SQL es JPQL (Java Persistance Query Language)
   *
   * @param pageable
   */
  @Query("SELECT u.username FROM User u WHERE u.username LIKE '%o'")
  public Page<String> findUsernames(Pageable pageable);
}
