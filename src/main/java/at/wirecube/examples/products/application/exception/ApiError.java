package at.wirecube.examples.products.application.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  private HttpStatus status;
  private String message;
  private List<String> errors;

  private ApiError() {
    timestamp = LocalDateTime.now();
  }

  public ApiError(HttpStatus status, String message) {
    this();
    this.status = status;
    this.message = message;
  }

  public ApiError(HttpStatus status, String message, List<String> errors) {
    this();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }
}
