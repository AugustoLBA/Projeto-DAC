package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.config.jwt.JwtToken;
import br.ifpb.dac.library_web.config.jwt.JwtUserDetailsService;
import br.ifpb.dac.library_web.dto.UserLogin;
import br.ifpb.dac.library_web.exception.infra.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private final JwtUserDetailsService detailsService;

    private final AuthenticationManager authenticationManager;
    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody @Valid UserLogin dto, HttpServletRequest request){
        log.info("Processo de autenticação pelo login {}",dto.getUsername());
        try{
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());
            return ResponseEntity.ok(token);

        }catch (AuthenticationException ex){
            log.warn("Bad credentials from username '{}' ", dto.getUsername());
        }
        return ResponseEntity.
                badRequest().
                body(new ErrorMessage(request, HttpStatus.BAD_REQUEST,"Credenciais Inválidas"));
    }

}
