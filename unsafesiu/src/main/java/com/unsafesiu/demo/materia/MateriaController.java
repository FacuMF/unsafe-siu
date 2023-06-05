package com.unsafesiu.demo.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/materia")
@RestController
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> obtenerMaterias(@RequestParam("idAlumno") int idAlumno) throws SQLException {

        List<MateriaDTO> materias = materiaService.listarMaterias(idAlumno);
        return ResponseEntity.ok(materias);

    }

}
