package com.jaroso.proyecto.apisensores.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final UserDetailsService userDetailsService;

  public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
  }

  private static final String EXCLUDED_AUTH_PATH = "/api/auth/";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    String requestPath = request.getRequestURI();

    // Si la ruta empieza por "/auth/", ignoramos la autenticación
    if (requestPath.startsWith(EXCLUDED_AUTH_PATH)) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = this.extractToken(request);
    if (jwtUtil.isValidToken(token)){
      String username = this.jwtUtil.getUsernameFromToken(token);
      UserDetails user = this.userDetailsService.loadUserByUsername(username);

      Authentication auth = new UsernamePasswordAuthenticationToken(
        user.getUsername(),
        user.getPassword(),
        user.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);

      filterChain.doFilter(request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

  }

  private String extractToken(HttpServletRequest request){
    //Al pasar el token desde Postman o el front se pasará como: "Bearer <token>"
    String bearerToken = request.getHeader("Authorization");
    if (bearerToken != null) {
      if (bearerToken.startsWith("Bearer")){
        return bearerToken.substring("Bearer ".length());
      }
    }

    return null;
  }

}

