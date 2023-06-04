package com.unsafesiu.demo.usuario.dao;

import com.unsafesiu.demo.datasource.PostgresDatasource;
import com.unsafesiu.demo.usuario.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDAO {

    @Autowired
    private PostgresDatasource postgresDatasource;

    public List<UsuarioDTO> selectUsuarios() throws SQLException {
        List<UsuarioDTO> usuarios = new ArrayList<>();
        String selectQuery = "SELECT * FROM USUARIO";
        Connection conn = postgresDatasource.getConnection();
        PreparedStatement selectPreparedStatement = conn.prepareStatement(selectQuery);

        ResultSet selectResultSet = selectPreparedStatement.executeQuery();
        while(selectResultSet.next()){
            UsuarioDTO usuario = new UsuarioDTO();

            usuario.setId(Integer.valueOf(selectResultSet.getString("ID")));
            usuario.setNombre(selectResultSet.getString("NOMBRE"));
            usuario.setApellido(selectResultSet.getString("APELLIDO"));
            usuario.setRol(selectResultSet.getString("ROL"));
            usuario.setUsuario(selectResultSet.getString("USUARIO"));
            usuario.setContrasenia(selectResultSet.getString("CONTRASENIA"));

            usuarios.add(usuario);

        }

        selectPreparedStatement.close();
        selectResultSet.close();

        return usuarios;
    }
}
