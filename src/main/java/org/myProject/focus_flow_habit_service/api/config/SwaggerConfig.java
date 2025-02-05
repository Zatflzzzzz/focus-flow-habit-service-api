package org.myProject.focus_flow_habit_service.api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                                new Server().url("http://localhost:8184")
                        )
                )
                .info(new Info()
                                .title("Focus Flow Habit Api")
                                .description("This is the Rest Api for Habit Service")
                                .version("v0.0.1")
                                .license(new License().name("Apache 2.0"))
                );
    }
}

