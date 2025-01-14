package restservice.producer.common;

import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class OpenApiInterceptor implements HandlerInterceptor {

  private final OpenAPI openAPI;
  private final OpenApiProperties openApiProperties;

  public OpenApiInterceptor(OpenAPI openAPI, OpenApiProperties openApiProperties) {
    this.openAPI = openAPI;
    this.openApiProperties = openApiProperties;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    String serverUrl = getServerUrl(request);
    openAPI.setServers(List.of(
        new Server().url(serverUrl).description("Default server"),
        new Server().url(openApiProperties.getServer())
            .description(openApiProperties.getServerDescription())
    ));
    return true;
  }

  private String getServerUrl(HttpServletRequest request) {
    String requestUrl = URLDecoder.decode(request.getRequestURL().toString(),
        StandardCharsets.UTF_8);
    return requestUrl.substring(0, requestUrl.length() - DEFAULT_API_DOCS_URL.length());
  }

}