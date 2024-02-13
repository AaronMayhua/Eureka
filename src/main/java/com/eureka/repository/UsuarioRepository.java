package com.eureka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eureka.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
