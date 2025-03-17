package br.ifpb.dac.library_web.config.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private br.ifpb.dac.library_web.entity.User usuario;
    public JwtUserDetails(br.ifpb.dac.library_web.entity.User usuario) {
        super(usuario.getEmail(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getType().name()));
        this.usuario = usuario;
    }

    public Long getId(){
        return usuario.getId();
    }

    public String getRole(){
        return usuario.getType().name();
    }
}
