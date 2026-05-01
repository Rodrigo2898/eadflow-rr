package com.rr.plataformaead.service;

import com.rr.plataformaead.entity.PessoaFisica;
import com.rr.plataformaead.entity.Role;
import com.rr.plataformaead.entity.dto.PessoaFisicaCadastroDTO;
import com.rr.plataformaead.entity.enums.PermissaoUsuario;
import com.rr.plataformaead.entity.enums.TipoPessoa;
import com.rr.plataformaead.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class PessoaFisicaAuthService extends BaseAuthService<PessoaFisica, PessoaFisicaCadastroDTO> {


    public PessoaFisicaAuthService(AuthenticationManager authenticationManager, UsuarioService usuarioService,
                                   RoleService roleService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        super(authenticationManager, usuarioService, roleService, passwordEncoder, jwtUtils);
    }

    @Override
    public void registrar(PessoaFisicaCadastroDTO pessoaFisicaCadastroDTO) {
        if (usuarioService.existsByEmail(pessoaFisicaCadastroDTO.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setEmail(pessoaFisicaCadastroDTO.getEmail());
        pessoaFisica.setNome(pessoaFisicaCadastroDTO.getNome());
        pessoaFisica.setCpf(pessoaFisicaCadastroDTO.getCpf());
        pessoaFisica.setTipoPessoa(TipoPessoa.FISICA);
        pessoaFisica.setAtivo(true);
        pessoaFisica.setDataCadastro(LocalDateTime.now());
        pessoaFisica.setSenha(passwordEncoder.encode(pessoaFisicaCadastroDTO.getSenha()));
        pessoaFisica.setDataNascimento(pessoaFisicaCadastroDTO.getDataNascimento());

        Set<String> strRoles = pessoaFisicaCadastroDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role usuarioRole = roleService.findByName(PermissaoUsuario.ALUNO)
                    .orElseThrow(() -> new RuntimeException("Erro: Permissao não encontrada"));
            roles.add(usuarioRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(PermissaoUsuario.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erro: Permissao não encontrada"));
                        roles.add(adminRole);
                        break;
                    case "instrutor":
                        Role instrutorRole = roleService.findByName(PermissaoUsuario.INSTRUTOR)
                                .orElseThrow(() -> new RuntimeException("Erro: Permissao não encontrada"));
                        roles.add(instrutorRole);
                        break;
                    default:
                        Role usuarioRole = roleService.findByName(PermissaoUsuario.ALUNO)
                                .orElseThrow(() -> new RuntimeException("Erro: Permissao não encontrada"));
                        roles.add(usuarioRole);
                }
            });
        }
        pessoaFisica.setRoles(roles);
        usuarioService.save(pessoaFisica);
    }
}
