package com.jaroso.proyecto.apisensores.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
@Setter
public class Response {

  private String message;

  public static ResponseEntity<?> newResponse(String message, HttpStatus status) {
    Response response = new Response(message);
    return ResponseEntity.status(status).body(response);
  }
}
