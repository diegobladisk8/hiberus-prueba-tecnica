package com.hiberus.payment.generated.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import jakarta.annotation.Generated;

/**
 * PaymentOrder
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-17T18:46:16.062694-05:00[America/Guayaquil]")
public class PaymentOrder {

  private String id;

  private String externalReference;

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

  private Account debtorAccount;

  private Account creditorAccount;

  private Amount instructedAmount;

  private String remittanceInformation;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate requestedExecutionDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastUpdateDate;

  public PaymentOrder() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentOrder(String id, String externalReference, StatusEnum status, Account debtorAccount, Account creditorAccount, Amount instructedAmount) {
    this.id = id;
    this.externalReference = externalReference;
    this.status = status;
    this.debtorAccount = debtorAccount;
    this.creditorAccount = creditorAccount;
    this.instructedAmount = instructedAmount;
  }

  public PaymentOrder id(String id) {
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

  public PaymentOrder externalReference(String externalReference) {
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

  public PaymentOrder status(StatusEnum status) {
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

  public PaymentOrder debtorAccount(Account debtorAccount) {
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

  public PaymentOrder creditorAccount(Account creditorAccount) {
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

  public PaymentOrder instructedAmount(Amount instructedAmount) {
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

  public PaymentOrder remittanceInformation(String remittanceInformation) {
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

  public PaymentOrder requestedExecutionDate(LocalDate requestedExecutionDate) {
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

  public PaymentOrder creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Fecha de creación de la orden
   * @return creationDate
  */
  @Valid 
  @Schema(name = "creationDate", example = "2023-11-15T10:30Z", description = "Fecha de creación de la orden", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("creationDate")
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PaymentOrder lastUpdateDate(OffsetDateTime lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
    return this;
  }

  /**
   * Fecha de última actualización
   * @return lastUpdateDate
  */
  @Valid 
  @Schema(name = "lastUpdateDate", example = "2023-11-15T10:30Z", description = "Fecha de última actualización", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    PaymentOrder paymentOrder = (PaymentOrder) o;
    return Objects.equals(this.id, paymentOrder.id) &&
        Objects.equals(this.externalReference, paymentOrder.externalReference) &&
        Objects.equals(this.status, paymentOrder.status) &&
        Objects.equals(this.debtorAccount, paymentOrder.debtorAccount) &&
        Objects.equals(this.creditorAccount, paymentOrder.creditorAccount) &&
        Objects.equals(this.instructedAmount, paymentOrder.instructedAmount) &&
        Objects.equals(this.remittanceInformation, paymentOrder.remittanceInformation) &&
        Objects.equals(this.requestedExecutionDate, paymentOrder.requestedExecutionDate) &&
        Objects.equals(this.creationDate, paymentOrder.creationDate) &&
        Objects.equals(this.lastUpdateDate, paymentOrder.lastUpdateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, externalReference, status, debtorAccount, creditorAccount, instructedAmount, remittanceInformation, requestedExecutionDate, creationDate, lastUpdateDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentOrder {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    externalReference: ").append(toIndentedString(externalReference)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    debtorAccount: ").append(toIndentedString(debtorAccount)).append("\n");
    sb.append("    creditorAccount: ").append(toIndentedString(creditorAccount)).append("\n");
    sb.append("    instructedAmount: ").append(toIndentedString(instructedAmount)).append("\n");
    sb.append("    remittanceInformation: ").append(toIndentedString(remittanceInformation)).append("\n");
    sb.append("    requestedExecutionDate: ").append(toIndentedString(requestedExecutionDate)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
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

