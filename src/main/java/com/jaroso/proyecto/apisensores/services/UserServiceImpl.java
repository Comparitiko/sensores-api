package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.UserRegisterDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return this.userRepository.findUserByUsername(username);
  }

  @Override
  public User save(UserRegisterDTO userRegisterDTO) {
    return null;
  }
}
