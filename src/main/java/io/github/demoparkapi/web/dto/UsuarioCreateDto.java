package io.github.demoparkapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UsuarioCreateDto {

    private String username;

    private String password;
}
