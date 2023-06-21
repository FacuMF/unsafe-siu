package com.unsafesiu.demo.curso;

import com.unsafesiu.demo.calificacion.CalificacionDTO;
import com.unsafesiu.demo.materia.MateriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoDAO cursoDAO;

    public List<MateriaDTO> listarMaterias(int idProfesor) throws SQLException {

        List<MateriaDTO> materias = cursoDAO.selectMateriasPorUsuario(idProfesor).orElse(null);
        return materias;
    }
    
    public List<CalificacionDTO> listarCalificaciones(Integer idProfesor, Integer idMateria) throws SQLException {

        List<CalificacionDTO> calificacionDTOS = cursoDAO.selectCalificacionesPorUsuarioYMateria(idProfesor, idMateria).orElse(null);
        return calificacionDTOS;
    }
    
    public void insertarCalificacion(CalificacionDTO calificacion)throws SQLException {
    	cursoDAO.insertarCalificaciones(calificacion);
    }
    
    public void actualizarCalificacion(CalificacionDTO calificacion)throws SQLException {
    	cursoDAO.actualizarCalificacion(calificacion);
    }
}
