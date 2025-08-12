package com.luruoyang.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author luruoyang
 */

@Component
public class JwtUtils {
  private final String KEY = "012345678901234567890";

  public String genToken(String username, List<String> roles) {
    Long expirationMs = 720000000L;
    return Jwts.builder()
        .setSubject(username)
        .claim("roles", roles)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
        .signWith(Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8)))
        .compact();
  }

  public boolean validate(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }

  public String getUsername(String token) {
    return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8))).build()
        .parseClaimsJws(token).getBody().getSubject();
  }

  public List<String> getRoles(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8))).build()
        .parseClaimsJws(token).getBody();
    Object r = claims.get("roles");
    if (r instanceof List) {
      return ((List<?>) r).stream().map(Object::toString).toList();
    }
    return List.of();
  }
}
