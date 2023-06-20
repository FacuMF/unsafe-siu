package com.unsafesiu.demo.usuario;

import com.unsafesiu.demo.datasource.PostgresDatasource;
import com.unsafesiu.demo.security.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAO {

    @Autowired
    private PostgresDatasource postgresDatasource;

    public List<Usuario> selectProfesores() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String selectQuery = "SELECT * FROM USUARIO WHERE rol = 'PROFESOR'";
        Connection conn = postgresDatasource.getConnection();
        PreparedStatement selectPreparedStatement = conn.prepareStatement(selectQuery);

        ResultSet selectResultSet = selectPreparedStatement.executeQuery();
        while(selectResultSet.next()){
            Usuario usuario = new Usuario();

            usuario.setId(Integer.valueOf(selectResultSet.getString("ID")));
            usuario.setNombre(selectResultSet.getString("NOMBRE"));
            usuario.setApellido(selectResultSet.getString("APELLIDO"));
            usuario.setRol(Role.valueOf(selectResultSet.getString("ROL")));
            usuario.setUsername(selectResultSet.getString("USUARIO"));
            usuario.setPassword(selectResultSet.getString("CONTRASENIA"));

            usuarios.add(usuario);

        }

        selectPreparedStatement.close();
        selectResultSet.close();

        return usuarios;
    }
    
    public Optional<Usuario> findByUsername(String username) {
        Optional<Usuario> usuarios = Optional.ofNullable(null);
        String selectQuery = "SELECT * FROM USUARIO WHERE USUARIO = '" + username + "'";
        Connection conn = postgresDatasource.getConnection();
        try {
	        PreparedStatement selectPreparedStatement = conn.prepareStatement(selectQuery);
	        
	        ResultSet selectResultSet = selectPreparedStatement.executeQuery();
	        
	        while(selectResultSet.next()){
	            Usuario usuario = new Usuario();
	
	            usuario.setId(Integer.valueOf(selectResultSet.getString("ID")));
	            usuario.setNombre(selectResultSet.getString("NOMBRE"));
	            usuario.setApellido(selectResultSet.getString("APELLIDO"));
	            usuario.setRol(Role.valueOf(selectResultSet.getString("ROL")));
	            usuario.setUsername(selectResultSet.getString("USUARIO"));
	            usuario.setPassword(selectResultSet.getString("CONTRASENIA"));
	            
	            usuarios = Optional.ofNullable(usuario);
	
	        }
	
	        selectPreparedStatement.close();
			selectResultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return usuarios;
    }
    
    public Optional<Usuario> findByUsernameAndPassword(String username, String password) {
        Optional<Usuario> usuarios = Optional.ofNullable(null);
        String selectQuery = "SELECT * FROM USUARIO WHERE USUARIO = '" + username + "' AND CONTRASENIA = '" + password + "'\nLIMIT 1";
        Connection conn = postgresDatasource.getConnection();
        try {
	        PreparedStatement selectPreparedStatement = conn.prepareStatement(selectQuery);
	        
	        ResultSet selectResultSet = selectPreparedStatement.executeQuery();
	        
	        if(selectResultSet.next()){
	        	
	            Usuario usuario = new Usuario();
	
	            usuario.setId(Integer.valueOf(selectResultSet.getString("ID")));
	            usuario.setNombre(selectResultSet.getString("NOMBRE"));
	            usuario.setApellido(selectResultSet.getString("APELLIDO"));
	            usuario.setRol(Role.valueOf(selectResultSet.getString("ROL")));
	            usuario.setUsername(selectResultSet.getString("USUARIO"));
	            usuario.setPassword(selectResultSet.getString("CONTRASENIA"));
	            
	            usuarios = Optional.ofNullable(usuario);
	
	        }
	
	        selectPreparedStatement.close();
			selectResultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return usuarios;
    }
}
