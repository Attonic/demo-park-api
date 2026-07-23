package io.github.demoparkapi;

import io.github.demoparkapi.web.dto.UsuarioCreateDto;
import io.github.demoparkapi.web.dto.UsuarioResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
@AutoConfigureWebTestClient
public class UsuarioIT {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsuario_ComUserNameEPasswordValidos_RetornaUsuarioCriadosComSatus201(){

        UsuarioResponseDto responseDto = testClient
                .post()
                .uri("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@email.com", "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseDto).isNotNull();
        Assertions.assertThat(responseDto.getId()).isNotNull();
        Assertions.assertThat(responseDto.getUsername()).isEqualTo("tody@email.com");
        Assertions.assertThat(responseDto.getRole()).isEqualTo("CLIENTE");
    }
}
