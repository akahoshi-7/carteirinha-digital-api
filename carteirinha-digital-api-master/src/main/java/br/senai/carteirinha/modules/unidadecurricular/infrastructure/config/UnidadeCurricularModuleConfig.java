package br.senai.carteirinha.modules.unidadecurricular.infrastructure.config;

import br.senai.carteirinha.modules.unidadecurricular.application.port.in.ListarUnidadesCurricularesUseCase;
import br.senai.carteirinha.modules.unidadecurricular.application.port.out.UnidadeCurricularRepository;
import br.senai.carteirinha.modules.unidadecurricular.application.service.ListarUnidadesCurricularesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnidadeCurricularModuleConfig {

    @Bean
    ListarUnidadesCurricularesUseCase
    listarUnidadesCurricularesUseCase(
        UnidadeCurricularRepository repository
    ) {
        return new ListarUnidadesCurricularesService(
            repository
        );
    }
}