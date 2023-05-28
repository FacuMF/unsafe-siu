package com.unsafesiu.demo.materia.dao;

import com.unsafesiu.demo.datasource.PostgresDatasource;
import com.unsafesiu.demo.materia.dto.MateriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MateriaDAO{

    @Autowired
    private PostgresDatasource postgresDatasource;
    public List<MateriaDTO> selectMateriasPorUsuario(int idAlumno) throws SQLException {

        List<MateriaDTO> materias = new ArrayList<>();
        String selectQuery = "SELECT m.ID, m.NOMBRE FROM MATERIA m JOIN INSCRIPCION i ON m.ID = i.ID_MATERIA " +
                "WHERE i.ID_ALUMNO = " + idAlumno;

        Connection conn = postgresDatasource.getConnection();
        PreparedStatement selectPreparedStatement = conn.prepareStatement(selectQuery);

        ResultSet selectResultSet = selectPreparedStatement.executeQuery();

        while(selectResultSet.next()){

            MateriaDTO materia = new MateriaDTO();
            materia.setId(selectResultSet.getInt("ID"));
            materia.setNombre(selectResultSet.getString("NOMBRE"));

            materias.add(materia);

        }

        return materias;
    }
}
