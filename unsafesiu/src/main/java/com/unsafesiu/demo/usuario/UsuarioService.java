package com.unsafesiu.demo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    public List<Usuario> listarProfesores() throws SQLException {

        List<Usuario> usuarios = usuarioDao.selectProfesores();
        return usuarios;

    }
    
    public Optional<Usuario> findByUsernameAndPassword(String username, String password){
    	return usuarioDao.findByUsernameAndPassword(username, password);
    }
}
