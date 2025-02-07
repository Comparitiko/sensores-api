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
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authManager;

  public UserController(UserService userService, JwtUtil jwtUtil, AuthenticationManager authManager) {
    this.userService = userService;
    this.jwtUtil = jwtUtil;
    this.authManager = authManager;
  }

  @PostMapping("/register")
  public ResponseEntity<?> save(@RequestBody UserRegisterDTO userDTO){
    return this.userService.save(userDTO);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginDTO){

    try {

    Authentication authDTO = new UsernamePasswordAuthenticationToken(
            loginDTO.username(),
            loginDTO.password()
    );

    // Autenticar usuario
    Authentication authentication = this.authManager.authenticate(authDTO);
      System.out.print("entra");

    // Obtener los detalles del usuario autenticado
    org.springframework.security.core.userdetails.User userdetails =
            (org.springframework.security.core.userdetails.User)
            authentication.getPrincipal();

    // Crear token JWT
    String token = this.jwtUtil.generateToken(authentication);

      // Devolver respuesta con el token y roles
      return ResponseEntity.ok(new LoginResponse(
              userdetails.getUsername(),
              userdetails.getAuthorities().stream()
                      .map(GrantedAuthority::getAuthority)
                      .toList(),
              token
      ));

    } catch (UsernameNotFoundException e) {
      return Response.newResponse("Username or password invalid", HttpStatus.BAD_REQUEST);
    }catch (Exception e) {
      System.out.println(e);
      return Response.newResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
