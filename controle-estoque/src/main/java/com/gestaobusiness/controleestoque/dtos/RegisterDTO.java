package com.gestaobusiness.controleestoque.dtos;

import com.gestaobusiness.controleestoque.models.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
