package com.rr.plataformaead.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisicaCadastroDTO extends BaseCadastroDTO{

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDateTime dataNascimento;

}
