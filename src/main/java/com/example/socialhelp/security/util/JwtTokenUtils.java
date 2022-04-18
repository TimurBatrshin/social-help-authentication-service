package com.example.socialhelp.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtils {

    public static String SECRET_KEY;

    public static final String ROLE = "role";

    public static final String EMAIL = "email";

    public static final String STATE = "state";

    public static final String ID = "id";

    @Value("${jwt.secret.key}")
    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

}
