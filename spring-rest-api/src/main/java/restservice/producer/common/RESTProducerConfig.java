package restservice.producer.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"restservice.producer", "org.springdoc"})
public class RESTProducerConfig implements WebMvcConfigurer {

  private final OpenApiInterceptor openApiInterceptor;

  @Autowired
  public RESTProducerConfig(OpenApiInterceptor openApiInterceptor) {
    this.openApiInterceptor = openApiInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(openApiInterceptor)
        .addPathPatterns("/**/v3/api-docs");
  }
}