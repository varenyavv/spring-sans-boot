package restservice.producer.common;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RESTProducerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  public static final String REST_PRODUCER_SERVLET_PATH = "/restproducer";

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
    return new String[]{REST_PRODUCER_SERVLET_PATH + "/*"};
  }

}
