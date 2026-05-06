package com.rr.plataformaead.service;

import com.rr.plataformaead.entity.Usuario;
import com.rr.plataformaead.entity.dto.BaseCadastroDTO;
import com.rr.plataformaead.entity.dto.JwtResponseDTO;
import com.rr.plataformaead.entity.dto.LoginRequestDTO;
import com.rr.plataformaead.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
public abstract class BaseAuthService<Entity extends Usuario, CadastroRequestDTO extends BaseCadastroDTO> {

    protected final AuthenticationManager authenticationManager;

    protected final UsuarioService usuarioService;

    protected final RoleService roleService;

    protected final PasswordEncoder passwordEncoder;

    protected final JwtUtils jwtUtils;

    abstract void registrar(CadastroRequestDTO cadastroRequestDTO);

    public JwtResponseDTO login(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (userDetails == null) {
            throw new RuntimeException("Usuario nao encontrado");
        }

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponseDTO(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}
