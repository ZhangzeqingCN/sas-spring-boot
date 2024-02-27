package com.example.template.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class MyJwtUtil {
    private final Algorithm algorithm = Algorithm.HMAC256("secret");

    public String createToken(String username) {
        return createToken(username, 60 * 1000);
    }

    private static final String identityKey = "t-username";

    /**
     *
     * @param token token
     * @return username
     */
    public String decodeToken(String token) {
        var verifier = JWT.require(algorithm).build();
        var jwt = verifier.verify(token);
        return jwt.getClaim(identityKey).asString();
    }

    public String createToken(String username, int seconds) {

        var calendar = Calendar.getInstance();
        var currentTime = calendar.getTime();
        calendar.add(Calendar.SECOND, seconds);
        var expirationTime = calendar.getTime();

        return JWT.create()
                .withSubject("authentication")
                .withIssuer("issuer")
                .withClaim(identityKey, username)
                .withIssuedAt(currentTime)
                .withExpiresAt(expirationTime)
                .sign(algorithm);
    }
}