package restservice.producer.common;

import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;
import static restservice.producer.common.RESTProducerInitializer.REST_PRODUCER_SERVLET_PATH;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.TreeMap;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SpringDocConfigProperties.ApiDocs;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for the tool that automatically creates OpenApi/Swagger documentation for the API
 * associated with this microservice.
 */
@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI(OpenApiProperties openApiProperties) {
    return new OpenAPI()
        .info(
            new Info()
                .title(openApiProperties.getTitle())
                .description(openApiProperties.getDescription())
                .version(openApiProperties.getVersion())
                .contact(buildContact(openApiProperties)))
        .addSecurityItem(new SecurityRequirement().addList("Secure Web Token"))
        .components(
            new Components()
                .addSecuritySchemes(
                    "Secure Web Token",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
        .extensions(openApiProperties.getXServiceLevelObjectives())
        .externalDocs(
            new ExternalDocumentation()
                .description("Terms of Service")
                .url(openApiProperties.getTermsOfServiceUrl()));
  }

  private Contact buildContact(OpenApiProperties openApiProperties) {
    return new Contact()
        .name(openApiProperties.getContactName())
        .email(openApiProperties.getContactEmail())
        .url(openApiProperties.getContactUrl());
  }

  @Bean
  public SpringDocConfigProperties springDocConfigProperties() {
    SpringDocConfigProperties props = new SpringDocConfigProperties();
    ApiDocs apiDocs = new ApiDocs();
    apiDocs.setPath(REST_PRODUCER_SERVLET_PATH + DEFAULT_API_DOCS_URL);
    props.setApiDocs(apiDocs);
    return props;
  }

  @Bean
  public SwaggerUiConfigProperties swaggerUiConfigProperties() {
    SwaggerUiConfigProperties props = new SwaggerUiConfigProperties();
    props.setDisableSwaggerDefaultUrl(true);
    props.setDisplayRequestDuration(true);
    props.setTryItOutEnabled(true);
    return props;
  }

  @Bean
  public OpenApiCustomizer sortSchemasAlphabetically() {
    return openApi -> {
      if (openApi.getComponents() != null && openApi.getComponents().getSchemas() != null) {
        openApi.getComponents().setSchemas(new TreeMap<>(openApi.getComponents().getSchemas()));
      }
    };
  }
}
