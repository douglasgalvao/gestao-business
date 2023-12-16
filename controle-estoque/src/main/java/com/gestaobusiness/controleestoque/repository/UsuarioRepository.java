package com.gestaobusiness.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaobusiness.controleestoque.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
