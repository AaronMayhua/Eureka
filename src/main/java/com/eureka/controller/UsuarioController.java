package com.eureka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestController;

import com.eureka.entity.Usuario;
import com.eureka.repository.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;
	
	@GetMapping("/lista")
	public List<Usuario> listarUsuarios(){
		return repository.findAll();
	}
	
	
	@GetMapping("/findByID/{id_user}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id_user") int id_user) {
        Optional<Usuario> buscado = repository.findById(id_user);
        
        if (buscado.isPresent()) {
            // Si lo encuentra, imprimirlo en la consola
        	// almacenar en una variable usuario, los atributos encontrados de la variable "buscado"
            Usuario usuario = buscado.get();
            // imprimirlo
            return ResponseEntity.ok(usuario);
        } else {
            // Si el usuario no existe, devolver una respuesta indicando que el usuario no fue encontrado
            
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el id " + id_user + " no existe"); 
        }
    }
	
	
	@PostMapping("/new")
	public ResponseEntity<String> saveUser(@RequestBody Usuario nuevo) {
		
		try {
			repository.save(nuevo);
			 return ResponseEntity.ok("Usuario registrado correctamente");
		}
		catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se registro el usuario");
		}
		 
	}
	
	
	 @PutMapping("/update/{id_user}")
	    public ResponseEntity<String> updateUser(@PathVariable int id_user, @RequestBody Usuario actualizarUsu) {
		 
		 try {
			 // buscar usuario
		        Usuario buscado = repository.findById(id_user)  // manejar error si no lo encuentra
		         .orElseThrow(() -> new RuntimeException("El usuario con el id" + id_user + " no existe"));

		        // actualizar campos
		        buscado.setNombre(actualizarUsu.getNombre());
		        buscado.setApellido(actualizarUsu.getApellido());
		        buscado.setEdad(actualizarUsu.getEdad());
		        buscado.setEmail(actualizarUsu.getEmail());
		        buscado.setFecha_registro(actualizarUsu.getFecha_registro());
		       
		        // save usuario update en la BD
		        repository.save(buscado);
		        
		        return ResponseEntity.ok("Usuario actualizado correctamente");
		 }
		 catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el id" + id_user + " no existe");
		}
	       
	    }
	
	
	@DeleteMapping("/delete/{id_user}")
	public ResponseEntity<String> delete(@PathVariable("id_user") int id_user) {
		 // ver si el usuario con un id especifico existe
        Optional<Usuario> buscado = repository.findById(id_user);
        
        //si existe, pasa aqui para la eliminacion del usuario encontrado con el ID
        if (buscado.isPresent()) {
            repository.deleteById(id_user);
            return ResponseEntity.ok("Usuario eliminado correctamente");
            
        } else {
            // si no existe el usuario con el id lanzado, mostrar msg que no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario con el id " + id_user + " no existe");
        }
	}
	
	
}
