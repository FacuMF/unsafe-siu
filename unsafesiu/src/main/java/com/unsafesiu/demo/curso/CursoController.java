package com.unsafesiu.demo.curso;

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

@RequestMapping("/curso")
@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Value("${secretKey}")
    private String secretKey;

    @GetMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<MateriaDTO>> obtenerCursos(@RequestHeader("Authorization") String token) throws SQLException {

        Claims claims = getTokenInfo(token);
        Integer idProfesor = claims.get("idProfesor", Integer.class);
        List<MateriaDTO> materias = cursoService.listarMaterias(idProfesor);
        return ResponseEntity.ok(materias);
    }

    @GetMapping(path = "/{idMateria}/calificaciones")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<CalificacionDTO>> obtenerCalificacionPorMateria(@RequestHeader("Authorization") String token, @PathVariable("idMateria") Integer idMateria) throws SQLException {
        Claims claims = getTokenInfo(token);
        Integer idProfesor = claims.get("idProfesor", Integer.class);

        List<CalificacionDTO> calificacionDTOS = cursoService.listarCalificaciones(idProfesor, idMateria);

        return ResponseEntity.ok(calificacionDTOS);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public void insertarCalificacion(@RequestHeader("Authorization") String token,@RequestBody CalificacionDTO calificacion) throws SQLException {
        Claims claims = getTokenInfo(token);
        Integer idProfesor = claims.get("idProfesor", Integer.class);
        cursoService.insertarCalificacion(idProfesor,calificacion);
    }
    
    @PutMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public void actualizarCalificacion(@RequestHeader("Authorization") String token,@RequestBody CalificacionDTO calificacion) throws SQLException {
        Claims claims = getTokenInfo(token);
        Integer idProfesor = claims.get("idProfesor", Integer.class);
        cursoService.actualizarCalificacion(idProfesor,calificacion);
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