package com.unsafesiu.demo.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.unsafesiu.demo.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	  @Value("${secretKey}")
	  private String secretKey;

	  private long jwtExpiration = 86400000;//un dia

	  private long refreshExpiration = 604800000;//siete dias

	  public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	  }

	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    final Claims claims = extractAllClaims(token);
	    return claimsResolver.apply(claims);
	  }

	  public String generateToken(Usuario usuario) {
	    return generateToken(new HashMap<>(), usuario);
	  }

	  public String generateToken(
	      Map<String, Object> extraClaims,
		  Usuario usuario
	  ) {
		  extraClaims.put("idAlumno", usuario.getId());
		  extraClaims.put("nombre", usuario.getNombre());
		  extraClaims.put("apellido", usuario.getApellido());
		  extraClaims.put("rol", usuario.getRol());
		  extraClaims.put("usuario", usuario.getUsername());
		  return buildToken(extraClaims, usuario, jwtExpiration);
	  }

	  public String generateRefreshToken(
	      UserDetails userDetails
	  ) {
	    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
	  }

	  private String buildToken(
	          Map<String, Object> extraClaims,
	          UserDetails userDetails,
	          long expiration
	  ) {
	    return Jwts
	            .builder()
	            .setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + expiration))
	            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
	            .compact();
	  }

	  public boolean isTokenValid(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	  }

	  private boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	  }

	  private Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	  }

	  private Claims extractAllClaims(String token) {
		  int i = token.lastIndexOf('.');
		  String withoutSignature = token.substring(0, i+1);
	    return Jwts
	        .parserBuilder()
	        //.setSigningKey(getSignInKey())
	        .build()
	        .parseClaimsJwt(withoutSignature)
	        .getBody();
	  }

	  private Key getSignInKey() {
	    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
	    return Keys.hmacShaKeyFor(keyBytes);
	  }
}
