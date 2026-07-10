package io.github.demoparkapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioSenhaDto {

    @NotBlank
    @Size(min = 6, max = 6)
    private String senhaAtual;

    @NotBlank
    @Size(min = 6, max = 6)
    private String novaSenha;

    @NotBlank
    @Size(min = 6, max = 6)
    private String confirmaSenha;


}
