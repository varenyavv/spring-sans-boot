package restservice.producer.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

/**
 * Properties class that is populated from one of the application.yml or application.properties
 * files that is used to populate the OpenApi/Swagger documentation information.
 */
@Validated
@PropertySource("classpath:/META-INF/open-api-doc.properties")
@Configuration
public class OpenApiProperties {

  /**
   * Title of the Swagger API.
   */
  @NotBlank
  @Value("${title}")
  private String title;

  /**
   * Short description of what this api does.
   */
  @NotBlank
  @Value("${description}")
  private String description;

  /**
   * Current semantic version of this API. Note that the company spec requires that this be a full
   * semantic version as defined here: <a href="https://semver.org/">...</a>
   */
  @NotBlank
  @Value("${version}")
  private String version;

  /**
   * The list of default media types that this API creates.
   */
  @NotNull
  @Size(min = 1)
  @Value("#{'${produces-mediatypes}'.split(',')}")
  private List<String> producesMediatypes;

  /**
   * The list of default media types that this API consumes.
   */
  @NotNull
  @Size(min = 1)
  @Value("#{'${consumes-mediatypes}'.split(',')}")
  private List<String> consumesMediatypes;

  /**
   * URL to the terms of service for this API. Required by:
   * <a href="https://api-docs.optum.com/standards/meta-information/">...</a>
   */
  @NotNull
  @Value("${terms-of-service-url}")
  private String termsOfServiceUrl;

  /**
   * Contact name of the team supporting this API.
   */
  @NotBlank
  @Value("${contact-name}")
  private String contactName;

  /**
   * Email address of the team supporting this API.
   */
  @NotBlank
  @Value("${contact-email}")
  private String contactEmail;

  /**
   * Optional URL of the team that supports this API.
   */
  @Value("${contact-url}")
  private String contactUrl;

  @NotNull
  @Value("#{${servers}}")
  private Map<String, Object> servers;

  /**
   * Required service level objectives that are defined here:
   * <a href="https://api-docs.optum.com/standards/meta-information/">...</a>
   */
  @NotNull
  @Value("#{${x-serviceLevelObjectives}}")
  private Map<String, Object> xServiceLevelObjectives;


  public List<String> getConsumesMediatypes() {
    return consumesMediatypes;
  }

  public String getContactName() {
    return contactName;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public String getContactUrl() {
    return contactUrl;
  }

  public List<String> getProducesMediatypes() {
    return producesMediatypes;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getVersion() {
    return version;
  }

  public String getTermsOfServiceUrl() {
    return termsOfServiceUrl;
  }

  public @NotNull Map<String, Object> getServers() {
    return servers;
  }

  public Map<String, Object> getXServiceLevelObjectives() {
    return xServiceLevelObjectives;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("title", title)
        .append("description", description)
        .append("version", version)
        .append("producesMediatypes", producesMediatypes)
        .append("consumesMediatypes", consumesMediatypes)
        .append("termsOfServiceUrl", termsOfServiceUrl)
        .append("contactName", contactName)
        .append("contactEmail", contactEmail)
        .append("contactUrl", contactUrl)
        .append("servers", servers)
        .append("xServiceLevelObjectives", xServiceLevelObjectives)
        .toString();
  }
}
