package la.foton.msf.auth.service;

import static la.foton.msf.auth.util.JwtTokenUtil.clearToken;

import java.net.InetAddress;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Serviço de validação de token.
 *
 * @author Vitor Sulzbach, Caio Riserio
 */
@Component
public class JwtTokenService {

    private final Algorithm algorithm;
    private final String host = InetAddress.getLocalHost().getHostAddress();
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${authorization.jwt.publicKey}") String publicKeyString) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Base64
                .getDecoder().decode(publicKeyString)));
        algorithm = Algorithm.RSA256(publicKey, null);
        verifier = JWT.require(algorithm).withIssuer(host).build();
    }

    /**
     * Extrai o header de autorização do request
     * @param request requisição que possui AUTHORIZATION header
     * @return Token de autorização se a requisição possuir uma,
     *          se não retorna null.
     */
    public String getJwtTokenFromRequest(final HttpServletRequest request){
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String bearerHead = "Bearer ";
        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith(bearerHead)){
            return null;
        }else{
            return authorizationHeader.substring(bearerHead.length());
        }
    }

    /**
     * Pega username do token.
     * @param jwtToken token limpo.
     * @return nome de usuário.
     * @throws JWTVerificationException caso o token não seja valido a função
     *                                  joga uma exceção que contem o motivo.
     */
    public String getUsernameFromToken(final String jwtToken) throws JWTVerificationException {
        return this.validateJwtToken(jwtToken).getSubject();
    }

    /**
     * Cria token de acesso.
     * @param user Usuario do token a ser gerado.
     * @return Token em formato String.
     */
    public String createAccessJwtToken(final UserDetails user) {
        return createJwtToken(user, 5);
    }

    /**
     * Cria refresh token.
     * @param user Usuario do token a ser gerado.
     * @return Token em formato String.
     */
    public String createRefreshJwtToken(final UserDetails user) {
        return createJwtToken(user, 30);
    }

    /**
     * Valida o token.
     * @param jwtToken token a ser validado.
     * @return token JWT decodificado.
     * @throws JWTVerificationException caso o token não seja valido a função
     *                                  joga uma exceção que contem o motivo.
     */
    public DecodedJWT validateJwtToken(final String jwtToken) throws JWTVerificationException {
        return verifier.verify(clearToken(jwtToken));
    }

    private String createJwtToken(final UserDetails user, Integer durationMinutes) {
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ durationMinutes * 60 * 1000))
                .withIssuer(host)
                .withClaim("roles",user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
    }

}