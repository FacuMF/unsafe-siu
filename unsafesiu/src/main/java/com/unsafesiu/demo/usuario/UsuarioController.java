package com.unsafesiu.demo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('RECTOR')")
    //@Secured({"ROLE_PROFESOR"})
    public ResponseEntity<List<Usuario>> obtenerProfesores() throws SQLException {
        List<Usuario> usuarios = usuarioService.listarProfesores();
        return ResponseEntity.ok(usuarios);
    }

}
