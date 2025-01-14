package restservice.producer.common;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RESTProducerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{RESTProducerConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/restproducer/*"};
  }
}
