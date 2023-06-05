package com.unsafesiu.demo.materia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaDAO materiaDAO;

    public List<MateriaDTO> listarMaterias(int idAlumno) throws SQLException {

        List<MateriaDTO> materias = materiaDAO.selectMateriasPorUsuario(idAlumno);
        return materias;
    }
}
