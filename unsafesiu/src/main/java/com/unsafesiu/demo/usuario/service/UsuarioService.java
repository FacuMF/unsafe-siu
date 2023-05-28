package com.unsafesiu.demo.usuario.service;

import com.unsafesiu.demo.usuario.dao.UsuarioDAO;
import com.unsafesiu.demo.usuario.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDao;

    public List<UsuarioDTO> listarUsuarios() throws SQLException {

        List<UsuarioDTO> usuarios = usuarioDao.selectUsuarios();
        return usuarios;

    }
}