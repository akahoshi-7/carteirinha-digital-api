package br.senai.carteirinha.modules.unidadecurricular.infrastructure.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
public class UnidadeCurricularDataInitializer {

    private static final UUID ALUNO_ID =
        UUID.fromString(
            "00000000-0000-0000-0000-000000000001"
        );

    private static final UUID MARIA_ID =
        UUID.fromString(
            "00000000-0000-0000-0000-000000000002"
        );

    @Bean
    CommandLineRunner carregarUnidadesCurricularesDeTeste(
        SpringDataUnidadeCurricularRepository repository
    ) {
        return args -> {

            if (repository.count() > 0) {
                return;
            }

            repository.saveAll(
                List.of(
                    new UnidadeCurricularJpaEntity(
                        UUID.fromString(
                            "10000000-0000-0000-0000-000000000001"
                        ),
                        ALUNO_ID,
                        "Banco de Dados",
                        "Prof. Carlos Silva",
                        8.5,
                        9.0,
                        1
                    ),
                    new UnidadeCurricularJpaEntity(
                        UUID.fromString(
                            "10000000-0000-0000-0000-000000000002"
                        ),
                        ALUNO_ID,
                        "Programação para Dispositivos Móveis",
                        "Prof. Ana Souza",
                        9.0,
                        8.0,
                        2
                    ),
                    new UnidadeCurricularJpaEntity(
                        UUID.fromString(
                            "10000000-0000-0000-0000-000000000003"
                        ),
                        ALUNO_ID,
                        "Desenvolvimento de Sistemas",
                        "Prof. Marcos Lima",
                        7.5,
                        8.5,
                        0
                    ),
                    new UnidadeCurricularJpaEntity(
                        UUID.fromString(
                            "10000000-0000-0000-0000-000000000004"
                        ),
                        ALUNO_ID,
                        "Testes de Software",
                        "Prof. Juliana Alves",
                        8.0,
                        9.5,
                        1
                    ),
                    new UnidadeCurricularJpaEntity(
                        UUID.fromString(
                            "20000000-0000-0000-0000-000000000001"
                        ),
                        MARIA_ID,
                        "Banco de Dados",
                        "Prof. Carlos Silva",
                        9.0,
                        9.0,
                        0
                    ),
                    new UnidadeCurricularJpaEntity(
                        UUID.fromString(
                            "20000000-0000-0000-0000-000000000002"
                        ),
                        MARIA_ID,
                        "Programação para Dispositivos Móveis",
                        "Prof. Ana Souza",
                        8.0,
                        8.5,
                        1
                    )
                )
            );
        };
    }
}