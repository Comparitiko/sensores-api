package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.UserRegisterDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(
    PasswordEncoder passwordEncoder,
    UserRepository userRepository
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return this.userRepository.findUserByUsername(username);
  }

  @Override
  public User save(UserRegisterDTO userRegisterDTO) {
    User user = new User();
    user.setUsername(userRegisterDTO.getUsername());
    user.setEmail(userRegisterDTO.getEmail());
    //Falta comprobación de que password2 es igual a password
    user.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
    return this.userRepository.save(user);
  }
}
