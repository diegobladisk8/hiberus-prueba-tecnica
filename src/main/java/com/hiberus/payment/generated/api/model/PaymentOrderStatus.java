package com.hiberus.payment.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaymentOrderStatus
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-17T18:46:16.062694-05:00[America/Guayaquil]")
public class PaymentOrderStatus {

  private String id;

  /**
   * Estado de la orden de pago
   */
  public enum StatusEnum {
    PENDING("PENDING"),

    PROCESSING("PROCESSING"),

    COMPLETED("COMPLETED"),

    REJECTED("REJECTED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private StatusEnum status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastUpdateDate;

  public PaymentOrderStatus() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentOrderStatus(String id, StatusEnum status, OffsetDateTime lastUpdateDate) {
    this.id = id;
    this.status = status;
    this.lastUpdateDate = lastUpdateDate;
  }

  public PaymentOrderStatus id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identificador único de la orden de pago
   * @return id
  */
  @NotNull
  @Schema(name = "id", example = "PO-0001", description = "Identificador único de la orden de pago", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PaymentOrderStatus status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Estado de la orden de pago
   * @return status
  */
  @NotNull
  @Schema(name = "status", example = "PENDING", description = "Estado de la orden de pago", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public PaymentOrderStatus lastUpdateDate(OffsetDateTime lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
    return this;
  }

  /**
   * Fecha de última actualización
   * @return lastUpdateDate
  */
  @NotNull @Valid
  @Schema(name = "lastUpdateDate", example = "2023-11-15T10:30Z", description = "Fecha de última actualización", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("lastUpdateDate")
  public OffsetDateTime getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(OffsetDateTime lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentOrderStatus paymentOrderStatus = (PaymentOrderStatus) o;
    return Objects.equals(this.id, paymentOrderStatus.id) &&
        Objects.equals(this.status, paymentOrderStatus.status) &&
        Objects.equals(this.lastUpdateDate, paymentOrderStatus.lastUpdateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, lastUpdateDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentOrderStatus {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    lastUpdateDate: ").append(toIndentedString(lastUpdateDate)).append("\n");
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

