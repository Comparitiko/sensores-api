package com.jaroso.proyecto.apisensores.security;

import com.jaroso.proyecto.apisensores.repositories.UserRepository;
import com.jaroso.proyecto.apisensores.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findUserByUsername(username).orElseThrow(
      () -> new UsernameNotFoundException(username + "not found")
    );


  }
}
