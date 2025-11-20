package com.hiberus.payment.domain.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Amount
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-20T12:44:16.896291-05:00[America/Guayaquil]")
public class Amount {

  private Double amount;

  private String currency;

  public Amount() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Amount(Double amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Amount amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Monto
   * @return amount
  */
  @NotNull 
  @Schema(name = "amount", example = "150.75", description = "Monto", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Amount currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Moneda
   * @return currency
  */
  @NotNull 
  @Schema(name = "currency", example = "USD", description = "Moneda", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Amount amount = (Amount) o;
    return Objects.equals(this.amount, amount.amount) &&
        Objects.equals(this.currency, amount.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Amount {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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

