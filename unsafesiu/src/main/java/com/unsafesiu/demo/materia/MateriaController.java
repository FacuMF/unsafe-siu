package com.unsafesiu.demo.materia;

import com.unsafesiu.demo.calificacion.CalificacionDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/materia")
@RestController
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @Value("${secretKey}")
    private String secretKey;

    @GetMapping
    @PreAuthorize("hasRole('ALUMNO')")
    public ResponseEntity<List<MateriaDTO>> obtenerMaterias(@RequestHeader("Authorization") String token) throws SQLException {

        Claims claims = getTokenInfo(token);
        Integer idAlumno = claims.get("id", Integer.class);
        List<MateriaDTO> materias = materiaService.listarMaterias(idAlumno);
        return ResponseEntity.ok(materias);

    }

    @GetMapping(path = "/{idMateria}/calificaciones")
    @PreAuthorize("hasRole('ALUMNO')")
    public ResponseEntity<List<CalificacionDTO>> obtenerCalificacionPorMateria(@RequestHeader("Authorization") String token, @PathVariable("idMateria") Integer idMateria) throws SQLException {
        Claims claims = getTokenInfo(token);
        Integer idAlumno = claims.get("id", Integer.class);

        List<CalificacionDTO> calificacionDTOS = materiaService.listarCalificaciones(idAlumno, idMateria);


        return ResponseEntity.ok(calificacionDTOS);
    }

    private Claims getTokenInfo(String token) {
        String jwtToken = token.substring(7);
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken);

        Claims claims = jws.getBody();
        return claims;
    }


}
