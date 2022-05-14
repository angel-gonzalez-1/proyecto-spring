package com.curso.proyectonum1.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.proyectonum1.model.Usuario;
import com.fasterxml.jackson.core.sym.Name;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//el repository hace referencia a nuestra base de datos y la transactional todos las acciones que podemos hacer
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

	@PersistenceContext
	private EntityManager  entityManager;
	@Override
	public List<Usuario> getUsuarios() {
		// TODO Auto-generated method stub
		//creando la consulta a hibernate, haciendo  referencia al usuario que es nuestra clase
		String query ="FROM Usuario ";
		return entityManager.createQuery(query).getResultList();
		
	}
	@Override
	public void eliminarUsuario(long id) {
		// TODO Auto-generated method stub
		//con este metodo estamos buscando el usurio y despues lo eliminamos
		Usuario usuario= entityManager.find(Usuario.class,id);
		entityManager.remove(usuario);
	}
	@Override
	public void registrarUsuarios(Usuario usuario) {
		// TODO Auto-generated method stub
		//con el objeto y la el merge en automtico busca y registra el nuevo usuarios
		entityManager.merge(usuario);
		
	}
	@Override
	public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
		// TODO Auto-generated method stub
		
		String query = "FROM Usuario WHERE email = :email ";
		List<Usuario> usuarioEncontrar= entityManager.createQuery(query)
				.setParameter("email", usuario.getEmail()).getResultList();
		if(usuarioEncontrar.isEmpty()) {
			return null;
		}
		//verificar la contrasenia con password hashed
		//optenemos el valor de la base de datos
		String passwordDashed= usuarioEncontrar.get(0).getPassword();
		Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		
		//verificamos los dos valores o dos contrasenias
		
		if(argon.verify(passwordDashed, usuario.getPassword())) {
			return usuarioEncontrar.get(0);
		}
		return null;
		
		/*
		 esto es lo mismo que el return devuelven el mismo valor
		if (usuarioEncontrar.isEmpty()) {
			return false;
			
		}else {
			return true;
		}*/
		
		
		
	}
	
	 
}
