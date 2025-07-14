package com.example.bibliogest.security;

import com.example.bibliogest.entity.usuario;
import com.example.bibliogest.repository.usuariorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class userdetailsserviceimpl implements UserDetailsService {

    @Autowired
    private usuariorepository usuariorepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        usuario usuario = usuariorepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        return new User(usuario.getDetalle().getEmail(), usuario.getPassword(), Collections.emptyList());
    }
}
