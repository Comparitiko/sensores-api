package com.jaroso.proyecto.apisensores.services;

import com.jaroso.proyecto.apisensores.dto.LoginRequest;
import com.jaroso.proyecto.apisensores.dto.LoginResponse;
import com.jaroso.proyecto.apisensores.dto.UserRegisterDTO;
import com.jaroso.proyecto.apisensores.entities.User;
import com.jaroso.proyecto.apisensores.repositories.UserRepository;
import com.jaroso.proyecto.apisensores.responses.Response;
import com.jaroso.proyecto.apisensores.security.JwtUtil;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authManager;
  private final JwtUtil jwtUtil;

  public UserServiceImpl(
    PasswordEncoder passwordEncoder,
    UserRepository userRepository,
    AuthenticationManager authManager,
    JwtUtil jwtUtil
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authManager = authManager;
    this.jwtUtil = jwtUtil;
  }

  public ResponseEntity<?> login(LoginRequest loginDTO) {
    try {

      Authentication authDTO = new UsernamePasswordAuthenticationToken(
        loginDTO.username(),
        loginDTO.password()
      );

      // Autenticar usuario
      Authentication authentication = this.authManager.authenticate(authDTO);

      // Obtener los detalles del usuario autenticado
      User usuario = (User) authentication.getPrincipal();

      // Crear token JWT
      String token = this.jwtUtil.generateToken(authentication);

      // Devolver respuesta con el token y roles
      return ResponseEntity.ok(new LoginResponse(
        usuario.getUsername(),
        usuario.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .toList(),
        token
      ));

    } catch (UsernameNotFoundException e) {
      return Response.newResponse("Username or password invalid", HttpStatus.BAD_REQUEST);
    }catch (Exception e) {
      return Response.newResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return this.userRepository.findUserByUsername(username);
  }

  @Override
  public ResponseEntity<?> save(UserRegisterDTO userRegisterDTO) {
    // Check if user already exists
    Optional<User> userDB = this.userRepository.findUserByUsernameOrEmail(
      userRegisterDTO.getUsername(),
      userRegisterDTO.getEmail()
    );

    if (userDB.isPresent()) {
      return Response.newResponse("User already exist", HttpStatus.BAD_REQUEST);
    }

    // Check if passwords match
    if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
      return Response.newResponse("Passwords do not match", HttpStatus.BAD_REQUEST);
    }

    // Create new user
    User newUser = new User();
    newUser.setUsername(userRegisterDTO.getUsername());
    newUser.setEmail(userRegisterDTO.getEmail());
    newUser.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));

    // Save user to database catching OptimisticLockingFailureException and any other exception
    try {
      this.userRepository.save(newUser);

      // Create the token
      Authentication authDTO = new UsernamePasswordAuthenticationToken(newUser.getUsername(), userRegisterDTO.getPassword());

      // Autenticar usuario
      Authentication authentication = this.authManager.authenticate(authDTO);

      // Obtener los detalles del usuario autenticado
      User usuario = (User) authentication.getPrincipal();

      // Crear token JWT
      String token = this.jwtUtil.generateToken(authentication);

      // Devolver respuesta con el token y roles
      return ResponseEntity.ok(new LoginResponse(
        usuario.getUsername(),
        usuario.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .toList(),
        token
      ));

    } catch (OptimisticLockingFailureException e) {
      return Response.newResponse("User already exists", HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.newResponse("Error saving user, try again later", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
