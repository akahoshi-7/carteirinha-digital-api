package br.senai.carteirinha.modules.usuario.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void deveResponderExatamenteComoLoginResponseDtoDoAndroid()
        throws Exception {

        mockMvc
            .perform(
                post("/auth/login")
                    .contentType(
                        MediaType.APPLICATION_JSON
                    )
                    .content(
                        """
                        {
                          "login": "aluno",
                          "senha": "123"
                        }
                        """
                    )
            )
            .andExpect(
                status().isOk()
            )
            .andExpect(
                content()
                    .contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON
                    )
            )
            .andExpect(
                jsonPath("$.id")
                    .value(
                        "00000000-0000-0000-0000-000000000001"
                    )
            )
            .andExpect(
                jsonPath("$.nome")
                    .value(
                        "Rafael Costa"
                    )
            )
            .andExpect(
                jsonPath("$.matricula")
                    .value(
                        "2026000001"
                    )
            )
            .andExpect(
                jsonPath("$.curso")
                    .value(
                        "Desenvolvimento de Sistemas"
                    )
            )
            .andExpect(
                jsonPath("$.turma")
                    .value(
                        "2DEVEST-B"
                    )
            )
            .andExpect(
                jsonPath(
                    "$.token",
                    matchesPattern(
                        "^[^.]+\\.[^.]+\\.[^.]+$"
                    )
                )
            )
            .andExpect(
                jsonPath("$.accessToken")
                    .doesNotExist()
            )
            .andExpect(
                jsonPath("$.refreshToken")
                    .doesNotExist()
            )
            .andExpect(
                jsonPath("$.usuario")
                    .doesNotExist()
            )
            .andExpect(
                jsonPath("$.senha")
                    .doesNotExist()
            );
    }

    @Test
    void deveAceitarLoginSemDiferenciarMaiusculas()
        throws Exception {

        mockMvc
            .perform(
                post("/auth/login")
                    .contentType(
                        MediaType.APPLICATION_JSON
                    )
                    .content(
                        "{\"login\":\"ALUNO\",\"senha\":\"123\"}"
                    )
            )
            .andExpect(
                status().isOk()
            )
            .andExpect(
                jsonPath("$.nome")
                    .value(
                        "Rafael Costa"
                    )
            );
    }

    @Test
    void deveResponder401ComMessageQuandoCredenciaisForemInvalidas()
        throws Exception {

        mockMvc
            .perform(
                post("/auth/login")
                    .contentType(
                        MediaType.APPLICATION_JSON
                    )
                    .content(
                        "{\"login\":\"aluno\",\"senha\":\"errada\"}"
                    )
            )
            .andExpect(
                status().isUnauthorized()
            )
            .andExpect(
                jsonPath("$.message")
                    .value(
                        "Login ou senha inválidos"
                    )
            )
            .andExpect(
                jsonPath("$.detail")
                    .doesNotExist()
            );
    }

    @Test
    void deveResponder400QuandoLoginOuSenhaNaoForemPreenchidos()
        throws Exception {

        mockMvc
            .perform(
                post("/auth/login")
                    .contentType(
                        MediaType.APPLICATION_JSON
                    )
                    .content(
                        "{\"login\":\"\",\"senha\":\"\"}"
                    )
            )
            .andExpect(
                status().isBadRequest()
            )
            .andExpect(
                jsonPath("$.message")
                    .value(
                        "Preencha login e senha"
                    )
            )
            .andExpect(
                jsonPath("$.errors.login")
                    .value(
                        "O login é obrigatório"
                    )
            )
            .andExpect(
                jsonPath("$.errors.senha")
                    .value(
                        "A senha é obrigatória"
                    )
            );
    }
}