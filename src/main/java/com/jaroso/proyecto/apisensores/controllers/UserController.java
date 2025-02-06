package com.jaroso.proyecto.apisensores.controllers;

import com.jaroso.proyecto.apisensores.dto.LoginRequest;
import com.jaroso.proyecto.apisensores.dto.LoginResponse;
import com.jaroso.proyecto.apisensores.dto.UserRegisterDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.security.JwtUtil;
import com.jaroso.proyecto.apisensores.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authManager;

  public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authManager) {
    this.userService = userService;
    this.jwtUtil = jwtUtil;
    this.authManager = authManager;
  }

  @PostMapping("/auth/register")
  public User save(@RequestBody UserRegisterDTO userDTO){
    return this.userService.save(userDTO);
  }

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody LoginRequest loginDTO){

    Authentication authDTO = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

    //Este método es el que llama al AuthenticationManager correspondiente para ver si la autenticación es correcta
    Authentication authentication = this.authManager.authenticate(authDTO);

    //El método nos devuelve un User (con UserDetailService) para con esos datos generar el token
    User user = (User) authentication.getPrincipal();

    String token = this.jwtUtil.generateToken(authentication);

    return new LoginResponse(user.getUsername(),
      user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
      token);
  }

}
