package com.curso.proyectonum1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//con esta anotacion hara referencia anuestra base de datos a una tabla en especifico
@Entity
@Table(name="usuario")
public class Usuario {
	//con las anotacion id nos dice que en nuestra tabla hace referencia que es id o primarikey
	//la anotacion Column nos dice que hace referencia a la columna en este caso del id y asi en las demas 
	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="password")
	private String password;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
