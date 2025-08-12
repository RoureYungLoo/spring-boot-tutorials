package com.luruoyang;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class TestJwt {
  @Test
  public void testCreateJwt() {

    Map<String, Object> headers = new HashMap<>();
    headers.put("header1", "value1");
    headers.put("header2", "value2");

    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", 1312937L);
    claims.put("username", "lisi");

    Date expiration = Date.from(Instant.now().plusSeconds((24 * 60 * 60)));

    SecretKey key = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes(StandardCharsets.UTF_8));

    String jwtToken = Jwts.builder()
        .header().add(headers).and()
        .claims(claims)
        .expiration(expiration)
        .signWith(key, Jwts.SIG.HS256)
        .compact();

    System.out.println(jwtToken);
  }

  @Test
  public void testParseJwt() {
    String jwtToken = "eyJoZWFkZXIyIjoidmFsdWUyIiwiaGVhZGVyMSI6InZhbHVlMSIsImFsZyI6IkhTMjU2In0.eyJ1c2VySWQiOjEzMTI5MzcsInVzZXJuYW1lIjoibGlzaSIsImV4cCI6MTc1NDQxNTAyOX0.ZbaIi_JRSLdo8TI1ClDhfhvNULqUvcD5MwcHCR5N71g";

    SecretKey key = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes(StandardCharsets.UTF_8));

    Map<String, Object> payload = Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(jwtToken)
        .getPayload();

    System.out.println(payload);

  }
}
