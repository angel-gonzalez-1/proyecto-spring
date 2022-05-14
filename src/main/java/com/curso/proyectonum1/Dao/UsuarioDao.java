package com.curso.proyectonum1.Dao;

import java.util.List;

import com.curso.proyectonum1.model.Usuario;

public interface UsuarioDao {

	List<Usuario> getUsuarios();

	void eliminarUsuario(long id);

	void registrarUsuarios(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
	
}
