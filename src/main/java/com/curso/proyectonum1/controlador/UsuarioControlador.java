package com.curso.proyectonum1.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.proyectonum1.Dao.UsuarioDao;
import com.curso.proyectonum1.model.Usuario;
import com.curso.proyectonum1.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

//esta anotacion nos permite redirijirnos por toda la aplicacion y sea compatible
@RestController
public class UsuarioControlador {

	
	//creando ojeto con el usuario dao crea al constructor y objeto
	@Autowired
	private UsuarioDao usuariodao;
	
	@Autowired
	private JWTUtil jwtUtil;
	//url para que regrese el contenido
	//el requestmapping nos busca en toda la pagina este valor o ruta y nos indicara que nos devolvera
	/*@RequestMapping(value = "prueba")
	public List<String> prueba(){
		List<String> ver = new ArrayList<>();
		ver.add("angel");
		ver.add("Ivan");
		//eturn List.of("manzana","mandarina");
		return ver;
		
	}*/
	//hace mas dinamico buscar el usuaio que se desa modificar con /{id}
	@RequestMapping(value= "usuario/{id}")
	public Usuario getUsuario(@PathVariable long id) {
		Usuario usuario= new Usuario();
		usuario.setId(id);
		usuario.setNombre("Angel");
		usuario.setApellido("Gonzalez");
		usuario.setEmail("angelgonzalezisic@gmail.com");
		usuario.setTelefono("5617666826");
		
		return usuario;
		
	}
	
	//metodo para obtener usuarios y listarlos de nuestro dao
	@RequestMapping(value= "api/usuarios")
	public List<Usuario> Usuario(@RequestHeader(value="Authorization") String token) {
		//validar el token en una request es con @requestheader y mandamos el token Authorization
		
		//optener el idUsuario a partir del token
		
		if(!validarToken(token)) {return null;}		
		return usuariodao.getUsuarios();
	 	
		
	}
	
	public boolean validarToken(String token) {
		String idUsuario=jwtUtil.getKey(token);
		return idUsuario != null;
	}
	//con el requesbody adqurimos el json y lo convertimos a usuario
	//en este metodo se usara para reistrar usuario
	@RequestMapping(value= "api/usuarios",method = RequestMethod.POST)
	public void registrarUsuario(@RequestBody Usuario usuario) {
		
		//encriptando con argon2 en bash la contrasenia
		Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String nuevaCon=argon.hash(1, 1024, 1, usuario.getPassword());
		usuario.setPassword(nuevaCon);
		usuariodao.registrarUsuarios(usuario);
	 	
		
	}
	
	
	@RequestMapping(value= "api/usuario/{id}", method =RequestMethod.DELETE)
	public void eliminarUsuario(@RequestHeader(value="Authorization") String token
			,@PathVariable long id ) {
		
		//con @PathVariable se adquiere a nuestro metodo la variable de nuestra url que se agrega al llamarlo
		
		if(validarToken(token)) {return;}
		Usuario usuario= new Usuario();
		usuariodao.eliminarUsuario(id);
		
		
	}
	
	
	
	
}
