package com.jaroso.proyecto.apisensores.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {
  private String username;

  private String email;

  private String password;

  @JsonProperty("confirm_password")
  private String confirmPassword;
}
