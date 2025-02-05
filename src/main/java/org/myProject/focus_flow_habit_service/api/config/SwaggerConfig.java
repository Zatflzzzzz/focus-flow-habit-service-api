package org.myProject.focus_flow_habit_service.api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port:8184}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:" + serverPort).description("Local development server")
                ))
                .info(new Info()
                        .title("Focus Flow Habit Service API")
                        .description("This service is designed to manage user habits, allowing users to create, edit, track, and delete habits, as well as log their completion. " +
                                "Habits help in forming useful rituals and achieving long-term goals. The API provides powerful tools for integration with other services and applications.")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Focus Flow Support")
                                .email("support@focusflow.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Additional Documentation")
                        .url("https://docs.focusflow.com/habit-service"));
    }
}
