package com.safra.open.cashless.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.safra.open.cashless.exception.ValidationException;
import com.safra.open.cashless.service.JwtTokenService;
import com.safra.open.cashless.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.safra.open.cashless.enums.ValidationErrorsEnum.AUTH_HEADER_INVALID;
import static com.safra.open.cashless.enums.ValidationErrorsEnum.NO_HEADER_AUTH;
import static com.safra.open.cashless.util.ResponseUtils.setResponseError;
import static java.util.Arrays.stream;

/**
 * Filtro de validação de token.
 * Para endpoints publicos: authorization.public.urls
 * Para roles em endpoints usar ex:	@RolesAllowed({"ROLE_USER", "ROLE_TI"}). 	@RolesAllowed("ROLE_USER")
 * Para pegar contexto de usuário ex: Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
 *
 * @author Vitor Sulzbach
 */
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenService jwtTokenService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
//            String token = getToken(request);
//            DecodedJWT decodedJWT = jwtTokenService.validateJwtToken(token);
//            setAuhenticationContext(request, decodedJWT);
            chain.doFilter(request, response);
        } catch (/*ValidationException | JWTVerificationException*/Exception e) {
            setResponseError(response, e.getMessage(), HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void setAuhenticationContext(HttpServletRequest request, DecodedJWT decodedJWT) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                decodedJWT.getSubject(), null,
                getAuthorities(decodedJWT)
        );
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(DecodedJWT decodedJWT) {
        final String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        final Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

    private String getToken(HttpServletRequest request) throws ValidationException {
        final String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (tokenHeader == null) {
            throw new ValidationException(NO_HEADER_AUTH);
        }
        if (!tokenHeader.startsWith("Bearer ")) {
            throw new ValidationException(AUTH_HEADER_INVALID);
        }
        return JwtTokenUtil.clearToken(tokenHeader);
    }

}