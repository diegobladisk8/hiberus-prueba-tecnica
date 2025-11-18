package com.hiberus.payment.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.hiberus.payment.generated.api.model.Account;
import com.hiberus.payment.generated.api.model.Amount;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaymentOrderRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-17T18:46:16.062694-05:00[America/Guayaquil]")
public class PaymentOrderRequest {

  private String externalReference;

  private Account debtorAccount;

  private Account creditorAccount;

  private Amount instructedAmount;

  private String remittanceInformation;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate requestedExecutionDate;

  public PaymentOrderRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentOrderRequest(String externalReference, Account debtorAccount, Account creditorAccount, Amount instructedAmount) {
    this.externalReference = externalReference;
    this.debtorAccount = debtorAccount;
    this.creditorAccount = creditorAccount;
    this.instructedAmount = instructedAmount;
  }

  public PaymentOrderRequest externalReference(String externalReference) {
    this.externalReference = externalReference;
    return this;
  }

  /**
   * Referencia externa única para la orden de pago
   * @return externalReference
  */
  @NotNull 
  @Schema(name = "externalReference", example = "EXT-1", description = "Referencia externa única para la orden de pago", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("externalReference")
  public String getExternalReference() {
    return externalReference;
  }

  public void setExternalReference(String externalReference) {
    this.externalReference = externalReference;
  }

  public PaymentOrderRequest debtorAccount(Account debtorAccount) {
    this.debtorAccount = debtorAccount;
    return this;
  }

  /**
   * Get debtorAccount
   * @return debtorAccount
  */
  @NotNull @Valid 
  @Schema(name = "debtorAccount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("debtorAccount")
  public Account getDebtorAccount() {
    return debtorAccount;
  }

  public void setDebtorAccount(Account debtorAccount) {
    this.debtorAccount = debtorAccount;
  }

  public PaymentOrderRequest creditorAccount(Account creditorAccount) {
    this.creditorAccount = creditorAccount;
    return this;
  }

  /**
   * Get creditorAccount
   * @return creditorAccount
  */
  @NotNull @Valid 
  @Schema(name = "creditorAccount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creditorAccount")
  public Account getCreditorAccount() {
    return creditorAccount;
  }

  public void setCreditorAccount(Account creditorAccount) {
    this.creditorAccount = creditorAccount;
  }

  public PaymentOrderRequest instructedAmount(Amount instructedAmount) {
    this.instructedAmount = instructedAmount;
    return this;
  }

  /**
   * Get instructedAmount
   * @return instructedAmount
  */
  @NotNull @Valid 
  @Schema(name = "instructedAmount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("instructedAmount")
  public Amount getInstructedAmount() {
    return instructedAmount;
  }

  public void setInstructedAmount(Amount instructedAmount) {
    this.instructedAmount = instructedAmount;
  }

  public PaymentOrderRequest remittanceInformation(String remittanceInformation) {
    this.remittanceInformation = remittanceInformation;
    return this;
  }

  /**
   * Información de remesa
   * @return remittanceInformation
  */
  
  @Schema(name = "remittanceInformation", example = "Factura 001-123", description = "Información de remesa", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("remittanceInformation")
  public String getRemittanceInformation() {
    return remittanceInformation;
  }

  public void setRemittanceInformation(String remittanceInformation) {
    this.remittanceInformation = remittanceInformation;
  }

  public PaymentOrderRequest requestedExecutionDate(LocalDate requestedExecutionDate) {
    this.requestedExecutionDate = requestedExecutionDate;
    return this;
  }

  /**
   * Fecha solicitada de ejecución
   * @return requestedExecutionDate
  */
  @Valid 
  @Schema(name = "requestedExecutionDate", example = "Thu Oct 30 19:00:00 ECT 2025", description = "Fecha solicitada de ejecución", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestedExecutionDate")
  public LocalDate getRequestedExecutionDate() {
    return requestedExecutionDate;
  }

  public void setRequestedExecutionDate(LocalDate requestedExecutionDate) {
    this.requestedExecutionDate = requestedExecutionDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentOrderRequest paymentOrderRequest = (PaymentOrderRequest) o;
    return Objects.equals(this.externalReference, paymentOrderRequest.externalReference) &&
        Objects.equals(this.debtorAccount, paymentOrderRequest.debtorAccount) &&
        Objects.equals(this.creditorAccount, paymentOrderRequest.creditorAccount) &&
        Objects.equals(this.instructedAmount, paymentOrderRequest.instructedAmount) &&
        Objects.equals(this.remittanceInformation, paymentOrderRequest.remittanceInformation) &&
        Objects.equals(this.requestedExecutionDate, paymentOrderRequest.requestedExecutionDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalReference, debtorAccount, creditorAccount, instructedAmount, remittanceInformation, requestedExecutionDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentOrderRequest {\n");
    sb.append("    externalReference: ").append(toIndentedString(externalReference)).append("\n");
    sb.append("    debtorAccount: ").append(toIndentedString(debtorAccount)).append("\n");
    sb.append("    creditorAccount: ").append(toIndentedString(creditorAccount)).append("\n");
    sb.append("    instructedAmount: ").append(toIndentedString(instructedAmount)).append("\n");
    sb.append("    remittanceInformation: ").append(toIndentedString(remittanceInformation)).append("\n");
    sb.append("    requestedExecutionDate: ").append(toIndentedString(requestedExecutionDate)).append("\n");
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

