package com.example.todosystem.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.Base64;

public class JwtUtil {
    private static final String signKey = "BlogSystem";
    private static final Long expire = 43200000L*30;//12h*30

    public static String getJWT(Map<String,Object> claims){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .compact();
    }

    public static Map<String,Object> parseJWT(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
