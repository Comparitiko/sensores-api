package com.jaroso.proyecto.apisensores.controllers;

import com.jaroso.proyecto.apisensores.dto.LoginRequest;
import com.jaroso.proyecto.apisensores.dto.LoginResponse;
import com.jaroso.proyecto.apisensores.dto.UserRegisterDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.responses.Response;
import com.jaroso.proyecto.apisensores.security.JwtUtil;
import com.jaroso.proyecto.apisensores.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authManager) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> save(@RequestBody UserRegisterDTO userDTO){
    return this.userService.save(userDTO);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginDTO){
    return this.userService.login(loginDTO);
  }
}
