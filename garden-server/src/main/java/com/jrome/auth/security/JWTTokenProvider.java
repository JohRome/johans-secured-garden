package com.jrome.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.security.Key;
import java.util.Date;

@Component
public class JWTTokenProvider {

    // @Value is used to get the secret key from the application.properties file
    @Value("${app.jwt-secret-key}")
    private String jwtSecretKey;

    // Same as above...
    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // The method parameter takes the Authentication object that's made from
    // the login() method inside the AuthServiceImpl.class
    public String generateJwtToken(Authentication authentication) {
        // The username or email that represents the authenticated user in the Authentication object
        String username = authentication.getName();

        Date currentDate = new Date();

        // Sets the expiration date of the JWT-Token
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        // Builds the JWT-Token with the provided values and then returns it
        return Jwts.builder()
                // Setting who's the JWT-Token is for, in this case, our registered admin
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                // The decoded secret key is needed to sign every new JWT-Token
                .signWith(key())
                // Serialize the JWT-Token to a String
                .compact();
    }


    // Returns a decoded secret key object from the application.properties file.
    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecretKey)
        );
    }

    // Extracts the username from a token
    public String getUsername(String jwtToken) {

        // Claims represents the user data
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJwt(jwtToken)
                .getBody();

        return claims.getSubject();
    }

    // Validate the JWT-Token
    public boolean validateJwtToken(String jwtToken) {
        try {
        Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parse(toString());
        return true;
        } catch (JwtException e) {
            throw new HttpStatusCodeException(HttpStatus.BAD_REQUEST, "Check your token, because it's faulty") {
            };
        }
    }
}