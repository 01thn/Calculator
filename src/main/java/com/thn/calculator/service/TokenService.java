package com.thn.calculator.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenService {
    private final static String secret = "secret";

    public static String createToken(String login) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer("thn dev")
                    .withSubject(login)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static boolean verifyToken(String token, String login) {
        boolean result = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("thn dev")
                    .withSubject(login)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            result = true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getLoginFromToken(String token) {
        String login = null;
        try {
            DecodedJWT jwt = JWT.decode(token);
            login = jwt.getSubject();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return login;
    }
}
