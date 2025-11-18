package com.hiberus.payment.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Error
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-17T18:46:16.062694-05:00[America/Guayaquil]")
public class Error {

  private URI type;

  private String title;

  private Integer status;

  private String detail;

  private URI instance;

  public Error() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Error(URI type, String title, Integer status) {
    this.type = type;
    this.title = title;
    this.status = status;
  }

  public Error type(URI type) {
    this.type = type;
    return this;
  }

  /**
   * URI que identifica el tipo de problema
   * @return type
  */
  @NotNull @Valid 
  @Schema(name = "type", example = "https://example.com/errors/payment-order-not-found", description = "URI que identifica el tipo de problema", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public URI getType() {
    return type;
  }

  public void setType(URI type) {
    this.type = type;
  }

  public Error title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Breve resumen del tipo de problema
   * @return title
  */
  @NotNull 
  @Schema(name = "title", example = "Payment Order Not Found", description = "Breve resumen del tipo de problema", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Error status(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * Código de estado HTTP
   * @return status
  */
  @NotNull 
  @Schema(name = "status", example = "404", description = "Código de estado HTTP", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Error detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Explicación detallada del problema
   * @return detail
  */
  
  @Schema(name = "detail", example = "The payment order with ID 'PO-0001' was not found", description = "Explicación detallada del problema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public Error instance(URI instance) {
    this.instance = instance;
    return this;
  }

  /**
   * URI que identifica la ocurrencia específica del problema
   * @return instance
  */
  @Valid 
  @Schema(name = "instance", example = "/payment-initiation/payment-orders/PO-0001", description = "URI que identifica la ocurrencia específica del problema", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("instance")
  public URI getInstance() {
    return instance;
  }

  public void setInstance(URI instance) {
    this.instance = instance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.type, error.type) &&
        Objects.equals(this.title, error.title) &&
        Objects.equals(this.status, error.status) &&
        Objects.equals(this.detail, error.detail) &&
        Objects.equals(this.instance, error.instance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, title, status, detail, instance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

