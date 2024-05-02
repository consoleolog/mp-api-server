package com.moonpool.mpapiserver.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.log4j.Log4j2;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Log4j2
public class JwtConfig {

    private static String key = "12312312314341242144124434989898797897878787";

    public static String generateToken(Map<String,Object> valueMap, Integer min){ //min 은 유효시간 이걸로 accesstoken 만드는거임
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor(JwtConfig.key.getBytes("UTF-8"));
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        String jwtStr = Jwts.builder()
                .setHeader(Map.of("type","Jwt"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
        return jwtStr;
    }
    public static Map<String, Object> validateToken(String token){
        Map<String, Object> claim = null;
        try {
            SecretKey key = Keys.hmacShaKeyFor(JwtConfig.key.getBytes("UTF-8"));
            claim = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException malformedJwtException) {
            throw new JwtException("Malformed");
        } catch (ExpiredJwtException expiredJwtException){
            throw new JwtException("Expired");
        } catch (InvalidClaimException invalidClaimException){
            throw new JwtException("Invalid");
        } catch ( JwtException jwtException){
            throw new JwtException("JwtError");
        } catch (Exception e){
            throw new JwtException("Error");
        }
        return claim;
    }
}
