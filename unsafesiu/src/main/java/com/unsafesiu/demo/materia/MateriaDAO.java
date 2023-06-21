package com.unsafesiu.demo.materia;

import com.unsafesiu.demo.calificacion.CalificacionDTO;
import com.unsafesiu.demo.datasource.PostgresDatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MateriaDAO{

    @Autowired
    private PostgresDatasource postgresDatasource;
    public Optional<List<MateriaDTO>> selectMateriasPorUsuario(int idAlumno) throws SQLException {

        List<MateriaDTO> materias = new ArrayList<>();
        String selectQuery = "SELECT m.ID, m.NOMBRE FROM MATERIA m JOIN INSCRIPCION i ON m.ID = i.ID_MATERIA " +
                "WHERE i.ID_ALUMNO = " + idAlumno;

        Connection conn = postgresDatasource.getConnection();
        PreparedStatement selectPreparedStatement = conn.prepareStatement(selectQuery);

        ResultSet selectResultSet = selectPreparedStatement.executeQuery();

        if(!selectResultSet.isBeforeFirst()){
            return Optional.empty();
        }

        while(selectResultSet.next()){

            MateriaDTO materia = new MateriaDTO();
            materia.setId(selectResultSet.getInt("ID"));
            materia.setNombre(selectResultSet.getString("NOMBRE"));

            materias.add(materia);

        }
        selectPreparedStatement.close();
        selectResultSet.close();

        return Optional.of(materias);
    }

    public Optional<List<CalificacionDTO>> selectCalificacionesPorUsuarioYMateria(Integer idAlumno, Integer idMateria) throws SQLException {

        String query = "select n.id , n.calificacion, n.descripcion_examen, n.id_curso, n.id_alumno from calificacion n , curso c where  "
        		+ "n.id_curso = c.id and n.id_alumno = " + idAlumno + " and c.id_materia = " + idMateria ;

        Connection conn = postgresDatasource.getConnection();
        PreparedStatement selectPreparedStatement = conn.prepareStatement(query);

        ResultSet selectResultSet = selectPreparedStatement.executeQuery();

        if(!selectResultSet.isBeforeFirst()){
            return Optional.empty();
        }

        List<CalificacionDTO> calificaciones = new ArrayList<>();

        while(selectResultSet.next()) {

            CalificacionDTO calificacionDTO = new CalificacionDTO();
            calificacionDTO.setId(selectResultSet.getInt("ID"));
            calificacionDTO.setId_curso(selectResultSet.getInt("ID_CURSO"));         
            calificacionDTO.setId_alumno(selectResultSet.getInt("ID_ALUMNO"));
            calificacionDTO.setCalificacion(selectResultSet.getBigDecimal("CALIFICACION"));
            calificacionDTO.setDescripcionNota((selectResultSet.getString("DESCRIPCION_EXAMEN")));

            calificaciones.add(calificacionDTO);

        }

        return Optional.ofNullable(calificaciones);
    }
}
