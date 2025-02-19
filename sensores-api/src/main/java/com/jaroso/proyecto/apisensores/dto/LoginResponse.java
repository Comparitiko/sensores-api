package com.jaroso.proyecto.apisensores.dto;

import java.util.List;

public record LoginResponse(String username, List<String> authorities, String token) {
}
