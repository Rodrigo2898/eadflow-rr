package com.rr.plataformaead.entity.dto;

import com.rr.plataformaead.entity.enums.TipoPessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@SuperBuilder(toBuilder = true)
public abstract class BaseCadastroDTO {

    @NotBlank
    @Email
    protected String email;

    @NotBlank
    protected String senha;

    protected String avatarUrl;

    @NotNull
    protected LocalDateTime dataCadastro;

    protected LocalDateTime ultimoAcesso;

    protected Set<String> roles;
}
