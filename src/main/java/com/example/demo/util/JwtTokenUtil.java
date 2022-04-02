package la.foton.msf.auth.util;

public class JwtTokenUtil {

    /**
     * Limpa o token. ex: "Bearer xxx.xxx.xxx" -> "xxx.xxx.xxx".
     *
     * @param token token a ser limpo.
     */
    public static String clearToken(String token){
        return token.replace("Bearer", "").trim();
    }

}