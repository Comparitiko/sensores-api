package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.UserRegisterDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.repositories.UserRepository;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    // Check if user already exists
    Optional<User> user = this.userRepository.findUserByUsernameOrEmail(
      userRegisterDTO.getUsername(),
      userRegisterDTO.getEmail()
    );

    if (user.isPresent()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User already exist"
      );
    }

    // Check if passwords match
    if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Passwords do not match"
      );
    }

    // Create new user
    User newUser = new User();
    newUser.setUsername(userRegisterDTO.getUsername());
    newUser.setEmail(userRegisterDTO.getEmail());
    newUser.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));

    // Save user to database catching OptimisticLockingFailureException and any other exception
    try {
      return this.userRepository.save(newUser);
    } catch (OptimisticLockingFailureException e) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User already exist"
      );
    } catch (Exception e) {
      throw new ResponseStatusException(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "Error saving user"
      );
    }
  }
}
