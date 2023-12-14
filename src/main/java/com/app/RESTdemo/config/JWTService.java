package com.app.RESTdemo.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

@Service
public class JWTService {

    private static final String SECRET_KEY = "24fe755251760a6ce70599e291f05bd05109b6def18470d1f44c34408836fe9eb735e0f4edaca056c4fb6d18ccba9618df4706ec";
    public String extractUsername(String jwt){
         return _extractClaims(jwt,Claims::getSubject);
    }

    public boolean isTokenValid(String jwt,UserDetails ud){
        final String username = extractUsername(jwt);
        return (username.equals(ud.getUsername())) && !_isTokenExpired(jwt);
    }

    private boolean _isTokenExpired(String jwt){
        return _extractExpiration(jwt).before(new Date());
    }

    public Date _extractExpiration(String jwt){
        return _extractClaims(jwt,Claims::getExpiration);
    }
    public String generateJWT(UserDetails ud){
        return generateJWT(new HashMap<>(),ud);
    }

    public String generateJWT(Map<String, Object> extraClaims, UserDetails ud){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(ud.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
                .signWith(_getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private <T> T _extractClaims(String jwt, Function<Claims, T > claimsResolver){
        final Claims claims = _extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }
    private Claims _extractAllClaims(String jwt){
        return Jwts
                .parserBuilder()
                .setSigningKey(_getSignInKey())
                .build()
                .parseClaimsJwt(jwt)
                .getBody();
    }

    private Key _getSignInKey(){
        byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(KeyBytes);
    }
}

