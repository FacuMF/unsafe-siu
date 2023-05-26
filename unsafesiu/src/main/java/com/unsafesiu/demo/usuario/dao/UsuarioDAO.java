package com.unsafesiu.demo.usuario.dao;

import com.unsafesiu.demo.usuario.dto.UsuarioDTO;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {

    List<UsuarioDTO> selectUsuarios() throws SQLException;
}
