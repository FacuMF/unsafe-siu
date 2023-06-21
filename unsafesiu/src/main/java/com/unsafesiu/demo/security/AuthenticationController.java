package com.unsafesiu.demo.security;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unsafesiu.demo.usuario.Usuario;
import com.unsafesiu.demo.usuario.UsuarioService;

@RequestMapping("/login")
@RestController
public class AuthenticationController {
	
	private class AuthDTO{
		public String token;
		public String rol;
		
		public AuthDTO(String token, String rol) {
			this.token = token;
			this.rol = rol;
		}
	}

	@Autowired
    private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;

    @PostMapping
    public ResponseEntity<AuthDTO> getToken(@RequestBody AuthenticationRequest body) throws SQLException {
    	
    	
    	String token = null;
        Usuario usuario = usuarioService.findByUsernameAndPassword(body.getUsername(), body.getPassword()).orElse(null);
        if(usuario == null) {
        	return new ResponseEntity<AuthDTO>(HttpStatus.UNAUTHORIZED);
        }
        token = jwtService.generateToken(usuario);
        return ResponseEntity.ok(new AuthDTO(token, usuario.getAuthorities().iterator().next().toString()));
    }
	
}
