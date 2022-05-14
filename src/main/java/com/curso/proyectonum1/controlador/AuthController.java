package com.curso.proyectonum1.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.proyectonum1.Dao.UsuarioDao;
import com.curso.proyectonum1.model.Usuario;
import com.curso.proyectonum1.utils.JWTUtil;

@RestController
public class AuthController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	//
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	public String loginUsuario(@RequestBody Usuario usuario) {
		
		
		Usuario usuariologeado=usuarioDao.obtenerUsuarioPorCredenciales(usuario);
		if (usuariologeado != null) {
			
			String tokenJwt = jwtUtil.create(String.valueOf(usuariologeado.getId()),usuariologeado.getEmail());
			return tokenJwt;
		}
		return "FAIL";
	 	
		
	}

}
