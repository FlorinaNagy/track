package edu.utcluj.track.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.StringTokenizer;

public class TokenUtils {

    /**
     * Function used to encode de token user session on login
     *
     * @param stringBuilder
     * @return
     */
    public static String encodeTokenFromUsername(StringBuilder stringBuilder) {
        String stringToken = stringBuilder.toString();
        byte[] byteToken = stringToken.getBytes();
        byte[] encodedToken = Base64.getEncoder().encode(byteToken);
        return new String(encodedToken);
    }

    /**
     * Function used to decode the username and password on logout action
     *
     * @param token
     * @return
     */
    public static String decodeUsernameFromToken(String token) {
        byte[] decoded = Base64.getDecoder().decode(token);
        String decodedToken = new String(decoded);
        StringTokenizer decodedTokenSplit = new StringTokenizer(decodedToken, ":");

        return decodedTokenSplit.nextToken();
    }



}

