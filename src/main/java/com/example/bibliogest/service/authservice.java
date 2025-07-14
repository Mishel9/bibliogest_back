package com.example.bibliogest.service;

import com.example.bibliogest.dto.authresponse;
import com.example.bibliogest.dto.loginrequest;
import com.example.bibliogest.dto.registerrequest;
import com.example.bibliogest.entity.detalleusuario;
import com.example.bibliogest.entity.rol;
import com.example.bibliogest.entity.usuario;
import com.example.bibliogest.repository.rolrepository;
import com.example.bibliogest.repository.usuariorepository;
import com.example.bibliogest.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class authservice {

    @Autowired
    private usuariorepository usuariorepository;

    @Autowired
    private rolrepository rolrepository;

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    private PasswordEncoder passwordencoder;

    @Autowired
    private AuthenticationManager authenticationmanager;

    public authresponse register(registerrequest request) {
        if (usuariorepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado.");
        }

        detalleusuario detalle = new detalleusuario();
        detalle.setNombre(request.getNombre());
        detalle.setEdad(request.getEdad());
        detalle.setEmail(request.getEmail());
        detalle.setDireccion(request.getDireccion());
        detalle.setTelefono(request.getTelefono());

        usuario nuevo = new usuario();
        nuevo.setEmail(request.getEmail());
        nuevo.setPassword(passwordencoder.encode(request.getPassword()));
        nuevo.setDetalle(detalle);
        detalle.setUsuario(nuevo);

        rol rolusuario = rolrepository.findByNombre("USER");
        if (rolusuario == null) {
            throw new RuntimeException("El rol 'USER' no existe en la base de datos.");
        }

        nuevo.setRol(rolusuario);
        usuariorepository.save(nuevo);

        String token = jwtutil.generarToken(nuevo.getEmail(), rolusuario.getNombre());

        return new authresponse(token, rolusuario.getNombre());
    }

    public authresponse login(loginrequest request) {
        authenticationmanager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        usuario user = usuariorepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtutil.generarToken(user.getEmail(), user.getRol().getNombre());

        return new authresponse(token, user.getRol().getNombre());
    }
}
