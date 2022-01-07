package com.demospring.usersapp.services;

import com.demospring.usersapp.entities.Role;
import com.demospring.usersapp.entities.User;
import com.demospring.usersapp.models.AuditDetails;
import com.demospring.usersapp.repositories.RoleRepository;
import com.demospring.usersapp.repositories.UserInRoleRepository;
import com.demospring.usersapp.securityRule.SecurityRule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
  @Autowired private UserInRoleRepository userInRoleRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private KafkaTemplate<Integer, String> kafkaTemplate;

  private ObjectMapper mapper = new ObjectMapper();

  private static final Logger log = LoggerFactory.getLogger(RoleService.class);

  public List<Role> getRoles() {
    return roleRepository.findAll();
  }

  public Role createRole(Role role) {
    Role saveRole = roleRepository.save(role);
    AuditDetails details =
        new AuditDetails(
            SecurityContextHolder.getContext().getAuthentication().getName(), role.getName());
    try {
      kafkaTemplate.send("demoTopic", mapper.writeValueAsString(details));
    } catch (JsonProcessingException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error Parsing the messagge");
    }
    return saveRole;
  }

  public Role updateRole(Integer roleId, Role role) {
    Optional<Role> result = roleRepository.findById(roleId);
    if (result.isPresent()) {
      return roleRepository.save(role);
    } else {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, String.format("Role ID %d do not exist", roleId));
    }
  }

  public void deleteRole(Integer roleId) {
    Optional<Role> result = roleRepository.findById(roleId);

    if (result.isPresent()) {
      roleRepository.delete(result.get());
    } else {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, String.format("Role ID %d do not exist", roleId));
    }
  }

  /**
   * Obtiene un User a partir de un rol
   *
   * @return List
   */
  @SecurityRule
  public List<User> getUserByRole(String roleName) {
    log.info("Getting Role by name {}", roleName);
    return userInRoleRepository.findUsersByRoleName(roleName);
  }
}
