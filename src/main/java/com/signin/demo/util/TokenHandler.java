package com.signin.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.signin.demo.secruity.LoginUser;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.util.Base64;

public class TokenHandler {

    public static LoginUser getUserFromToken(String token) {
        return (token != null) ? fromJSON(fromBase64(token)) : null;
    }

    private static final String UTF_8 = "UTF-8";

    public static String createToken(String username, String role, long expires) {
        LoginUser user = new LoginUser();
        user.setUsername(username);
        user.setRole(role);
        user.setExpires(expires);
        final StringBuilder sb = new StringBuilder(170);
        sb.append(toBase64(toJSON(user)));
        return sb.toString();
    }

    private static LoginUser fromJSON(final byte[] userBytes) {
        try {
            return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes),
                    LoginUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    private static byte[] toJSON(LoginUser user) {
        try {
            return new ObjectMapper().writeValueAsBytes(user);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String toBase64(byte[] content) {
        String result = Base64.getEncoder().encodeToString(content);
        // String result = new String(Base64.encodeToString(content), Base64.NO_WRAP);
        return result;
    }

    private static byte[] fromBase64(String content) {
        return DatatypeConverter.parseBase64Binary(content);
    }

    public static boolean validateToken(String authToken) {
        LoginUser user = getUserFromToken(authToken);
        long expires = user.getExpires();

        if ((user != null) && (expires > System.currentTimeMillis())) {
            return true;
        }

        return false;
    }
}