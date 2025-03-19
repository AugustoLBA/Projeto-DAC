package br.ifpb.dac.library_web.config.jwt;

import br.ifpb.dac.library_web.entity.User;
import br.ifpb.dac.library_web.enumeration.TypeUser;
import br.ifpb.dac.library_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService usuarioService;

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = usuarioService.findUserByEmail(email);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String email) {
        TypeUser role = usuarioService.findUserByType(email);
        return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));
    }*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = usuarioService.findUserByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        return new JwtUserDetails(usuario);  // Cria o JwtUserDetails a partir do usuário encontrado
    }

    // Gera o token JWT para o usuário autenticado
    public JwtToken getTokenAuthenticated(String email) {
        // Aqui você deve buscar o usuário pelo email
        User usuario = usuarioService.findUserByEmail(email);

        // Verifica se o usuário foi encontrado
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        // Obtém o papel (role) do usuário
        TypeUser role = usuario.getType();  // Supondo que o papel esteja na entidade User

        // Gera o token com o papel do usuário
        return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));  // Cria o JWT, removendo o prefixo "ROLE_"
    }
}
