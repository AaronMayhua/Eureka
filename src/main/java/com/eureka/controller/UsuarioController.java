package com.eureka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestController;

import com.eureka.entity.Usuario;
import com.eureka.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;
	
	@GetMapping("/lista")
	public List<Usuario> listarUsuarios(){
		return repository.findAll();
	}
	
	@GetMapping("/{id_user}")
	public Optional<Usuario> buscarPorId(@PathVariable("id_user") int id_user) {
		return repository.findById(id_user);
	}
	
	@PostMapping("/new")
	public void saveUser(@RequestBody Usuario nuevo) {
		 repository.save(nuevo);
	}
	
	@DeleteMapping("/{id_user}")
	public void delete(@PathVariable("id_user") int id_user) throws Exception {
		repository.deleteById(id_user);
	}
}
