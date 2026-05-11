package com.rr.plataformaead.controller;

import com.rr.plataformaead.entity.dto.JwtResponseDTO;
import com.rr.plataformaead.entity.dto.LoginRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface AuthController<SignUpRequestDTO> {

    @PostMapping("/signup")
    ResponseEntity<String> registerUser(@RequestBody SignUpRequestDTO signUpRequestDTO);

    @PostMapping("/signin")
    ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO);

}
