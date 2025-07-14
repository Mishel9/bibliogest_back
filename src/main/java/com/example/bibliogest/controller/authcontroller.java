package com.example.bibliogest.controller;

import com.example.bibliogest.dto.authresponse;
import com.example.bibliogest.dto.loginrequest;
import com.example.bibliogest.dto.registerrequest;
import com.example.bibliogest.service.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // 🔓 Permite peticiones desde Flutter/web local
public class authcontroller {

    @Autowired
    private authservice authService;

    // 📌 Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<authresponse> login(@RequestBody loginrequest request) {
        try {
            authresponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new authresponse("Error al iniciar sesión: " + e.getMessage()));
        }
    }

    // 📌 Registrarse
    @PostMapping("/register")
    public ResponseEntity<authresponse> register(@RequestBody registerrequest request) {
        try {
            authresponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .body(new authresponse("Error al registrarse: " + e.getMessage()));
        }
    }
}
