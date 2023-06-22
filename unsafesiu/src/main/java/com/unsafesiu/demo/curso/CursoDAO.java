package com.unsafesiu.demo.curso;

import com.unsafesiu.demo.calificacion.CalificacionDTO;
import com.unsafesiu.demo.calificacion.CalificacionesProfesorDTO;
import com.unsafesiu.demo.materia.MateriaDTO;
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
public class CursoDAO{

    @Autowired
    private PostgresDatasource postgresDatasource;
    public Optional<List<MateriaDTO>> selectMateriasPorUsuario(int idProfesor) throws SQLException {

        List<MateriaDTO> materias = new ArrayList<>();
        String selectQuery = "SELECT m.id, m.nombre FROM Curso c, Usuario u, Materia m "
        		+ "where  c.ID_Profesor = u.ID and c.ID_Materia = m.ID and c.ID_Profesor =  " + idProfesor;

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
    
    public Optional<List<CalificacionesProfesorDTO>> selectCalificacionesPorUsuarioYMateria(Integer idProfesor, Integer idMateria) throws SQLException {

        String query = "SELECT n.id, n.calificacion, n.descripcion_examen, u.nombre, u.apellido, n.id_curso FROM Curso c, Usuario u, calificacion n "
        		+ "where  n.ID_alumno = u.ID and n.id_curso = c.id and c.ID_Materia = " +idMateria+" and c.ID_Profesor = " + idProfesor + 
        		"\nORDER BY u.nombre";

        Connection conn = postgresDatasource.getConnection();
        PreparedStatement selectPreparedStatement = conn.prepareStatement(query);

        ResultSet selectResultSet = selectPreparedStatement.executeQuery();

        if(!selectResultSet.isBeforeFirst()){
            return Optional.empty();
        }

        List<CalificacionesProfesorDTO> calificaciones = new ArrayList<>();

        while(selectResultSet.next()) {

            CalificacionesProfesorDTO calificacionDTO = new CalificacionesProfesorDTO();
            calificacionDTO.setId(selectResultSet.getInt("ID"));
            calificacionDTO.setId_curso(selectResultSet.getInt("ID_CURSO"));
            calificacionDTO.setNombre(selectResultSet.getString("NOMBRE"));//TODO: cambiar a otro DTO
            calificacionDTO.setApellido(selectResultSet.getString("APELLIDO"));
            calificacionDTO.setCalificacion(selectResultSet.getBigDecimal("CALIFICACION"));
            calificacionDTO.setDescripcionNota((selectResultSet.getString("DESCRIPCION_EXAMEN")));

            calificaciones.add(calificacionDTO);

        }

        return Optional.ofNullable(calificaciones);
    }
    
    public void insertarCalificaciones(CalificacionDTO calificacion)throws SQLException  {
    	 String insertQuery = "insert into calificacion (id_curso, id_alumno, calificacion, descripcion_examen) "
    	 		+ "values ("+calificacion.getId_curso()+" , "+calificacion.getId_alumno()+" , "+ calificacion.getCalificacion()+" , '"+ calificacion.getDescripcionNota()+"')";
    	 Connection conn = postgresDatasource.getConnection();
    	 PreparedStatement insertPreparedStatement = conn.prepareStatement(insertQuery);
    	 insertPreparedStatement.executeUpdate();
    	 insertPreparedStatement.close();
    }
    
    public void actualizarCalificacion(CalificacionDTO calificacion)throws SQLException  {
   	 String insertQuery = "UPDATE calificacion  "
   	 		+ " SET calificacion =  "+calificacion.getCalificacion()+" , descripcion_examen = '" + calificacion.getDescripcionNota() + "'"
   	 		+ " WHERE id = " + calificacion.getId();
   	 Connection conn = postgresDatasource.getConnection();
   	 PreparedStatement insertPreparedStatement = conn.prepareStatement(insertQuery);
   	 insertPreparedStatement.executeUpdate();
   	 insertPreparedStatement.close();
   }
    
}
