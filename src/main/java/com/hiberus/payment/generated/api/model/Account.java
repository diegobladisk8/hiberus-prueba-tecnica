package com.hiberus.payment.generated.api.model;

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
 * Account
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-17T18:46:16.062694-05:00[America/Guayaquil]")
public class Account {

  private String iban;

  public Account() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Account(String iban) {
    this.iban = iban;
  }

  public Account iban(String iban) {
    this.iban = iban;
    return this;
  }

  /**
   * IBAN de la cuenta
   * @return iban
  */
  @NotNull
  @Schema(name = "iban", example = "EC12DEBTOR", description = "IBAN de la cuenta", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("iban")
  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.iban, account.iban);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
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

