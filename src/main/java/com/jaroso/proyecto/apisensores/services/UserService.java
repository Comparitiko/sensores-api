package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.UserRegisterDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
  public Optional<User> findByUsername(String username);
  public User save(UserRegisterDTO userRegisterDTO);
}
