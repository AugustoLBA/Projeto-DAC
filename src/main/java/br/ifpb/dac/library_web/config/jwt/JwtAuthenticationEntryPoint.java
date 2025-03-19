package br.ifpb.dac.library_web.config.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /*
    /Classe responsavel por lançar a exceção com status 401 quando usuários
    não autenticados tentarem acessar recursos que necessitem de autenticação.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("Http Status 401 {}", authException.getMessage());
        response.setHeader("www-authenticate", "Bearer realm='/api/v1/auth'");
        response.sendError(401);
    }
}
