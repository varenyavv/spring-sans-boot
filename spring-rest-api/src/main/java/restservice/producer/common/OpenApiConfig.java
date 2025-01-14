package restservice.producer.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for the tool that automatically creates OpenApi/Swagger documentation for the API
 * associated with this microservice.
 */
@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

  private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiConfig.class);

  @Bean
  public OpenAPI gpsRestOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Test Rest APIs")
                .description("APIs for accessing Test resources.")
                .version("1")
                .contact(buildContact()))
        .addSecurityItem(new SecurityRequirement().addList("Secure Web Token"))
        .components(
            new Components()
                .addSecuritySchemes(
                    "Secure Web Token",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
        .servers(
            List.of(new Server().url("http://localhost:8080/api").description("Localhost server")));
  }

  private Contact buildContact() {
    return new Contact().name("VV-org");
  }
}
