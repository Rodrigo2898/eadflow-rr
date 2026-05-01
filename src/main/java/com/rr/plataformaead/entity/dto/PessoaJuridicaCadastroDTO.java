package com.rr.plataformaead.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
public class PessoaJuridicaCadastroDTO extends BaseCadastroDTO {

    @CNPJ
    private String cnpj;

    @NotBlank
    private String razaoSocial;

    @NotNull
    private LocalDateTime dataAbertura;

}
