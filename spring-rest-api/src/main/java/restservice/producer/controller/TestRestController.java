package restservice.producer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
@Tag(name = "test-rest-controller", description = "Test Rest Controller")
public class TestRestController {

  @RequestMapping(
      value = "/v1",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "HTTP_201_CREATED"),
          @ApiResponse(responseCode = "400", description = "HTTP_400_BAD_REQUEST"),
          @ApiResponse(responseCode = "409", description = "HTTP_409_CONFLICT"),
          @ApiResponse(responseCode = "415", description = "HTTP_415_UNSUPPORTED_MEDIA_TYPE"),
          @ApiResponse(responseCode = "500", description = "HTTP_500_INTERNAL_SERVER_ERROR")
      })
  @Operation(summary = "Test API", description = "Test API to check if the service is up")
  public ResponseEntity<String> testApi() {
    return new ResponseEntity<>("Nice", HttpStatus.OK);
  }
}
