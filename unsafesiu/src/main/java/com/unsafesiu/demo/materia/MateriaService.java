package com.unsafesiu.demo.materia;

import com.unsafesiu.demo.calificacion.CalificacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaDAO materiaDAO;

    public List<MateriaDTO> listarMaterias(int idAlumno) throws SQLException {

        List<MateriaDTO> materias = materiaDAO.selectMateriasPorUsuario(idAlumno).orElse(null);
        return materias;
    }

    public List<CalificacionDTO> listarCalificaciones(Integer idAlumno, Integer idMateria) throws SQLException {

        List<CalificacionDTO> calificacionDTOS = materiaDAO.selectCalificacionesPorUsuarioYMateria(idAlumno, idMateria).orElse(null);
        return calificacionDTOS;
    }
}
