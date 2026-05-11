package com.rr.plataformaead.controller.impl;

import com.rr.plataformaead.controller.AuthController;
import com.rr.plataformaead.entity.dto.JwtResponseDTO;
import com.rr.plataformaead.entity.dto.LoginRequestDTO;
import com.rr.plataformaead.entity.dto.PessoaFisicaCadastroDTO;
import com.rr.plataformaead.service.PessoaFisicaAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/pessoa-fisica")
public class PessoaFisicaAuthController implements AuthController<PessoaFisicaCadastroDTO> {

    private final PessoaFisicaAuthService authService;

    public PessoaFisicaAuthController(PessoaFisicaAuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<String> registerUser(@RequestBody PessoaFisicaCadastroDTO dto) {
        this.authService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado com sucesso");
    }

    @Override
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(loginRequestDTO));
    }
}
