package com.eureka.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class Usuario {
	                 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_user;
	
	@Column(name = "nombre", length = 40)
	private String nombre;
	
	@Column(name = "apellido", length = 40)
	private String apellido;
	
	@Column(name = "email", length = 50)
	private String email;
	
	@Column(name = "edad")
	private int edad;
	
	@Column(name = "fecha_registro")
	@Temporal(TemporalType.DATE)
	private Date fecha_registro;
	
}
