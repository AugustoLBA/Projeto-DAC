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
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario= usuarioService.findUserByEmail(email);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String email){
        TypeUser role = usuarioService.findUserByType(email);
        return JwtUtils.createToken(email, role.name().substring("ROLE_".length()));
    }
}
