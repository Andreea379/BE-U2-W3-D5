package com.example.GestioneEventi.security.jwt;

import com.example.GestioneEventi.security.services.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirations;

    public String createJwtToken(Authentication autenticazione){

        UserDetailsImpl utentePrincipal = (UserDetailsImpl) autenticazione.getDetails();

        return Jwts.builder()
                .setSubject(utentePrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirations))
                .signWith(recoverChiave(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String recoverUsernameDaToken(String token){
        return Jwts.parserBuilder().setSigningKey(recoverChiave()).build().parseClaimsJwt(token).getBody().getSubject();
    }

    public Date recoverExpirationFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(recoverChiave()).build().parseClaimsJwt(token).getBody().getExpiration();
    }

    public boolean validationJwtToken(String token){
        Jwts.parserBuilder().setSigningKey(recoverChiave()).build().parse(token);
        return true;
    }

    public Key recoverChiave(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
