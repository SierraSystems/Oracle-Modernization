package com.nttdata.quarkus.management.api.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Contact
 */
@ApiModel(description = "Contact")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-10-20T07:29:34.739-07:00[America/Los_Angeles]")

public class Contact {
  @JsonProperty("contactId")
  private BigDecimal contactId;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("email")
  private String email;

  @JsonProperty("phoneNumber")
  private String phoneNumber;

  @JsonProperty("customerId")
  private BigDecimal customerId;

  public Contact contactId(BigDecimal contactId) {
    this.contactId = contactId;
    return this;
  }

  /**
   * Get contactId
   * @return contactId
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getContactId() {
    return contactId;
  }

  public void setContactId(BigDecimal contactId) {
    this.contactId = contactId;
  }

  public Contact firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  */
  @ApiModelProperty(value = "")


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Contact lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  */
  @ApiModelProperty(value = "")


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Contact email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Contact phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  */
  @ApiModelProperty(value = "")


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Contact customerId(BigDecimal customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getCustomerId() {
    return customerId;
  }

  public void setCustomerId(BigDecimal customerId) {
    this.customerId = customerId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contact contact = (Contact) o;
    return Objects.equals(this.contactId, contact.contactId) &&
        Objects.equals(this.firstName, contact.firstName) &&
        Objects.equals(this.lastName, contact.lastName) &&
        Objects.equals(this.email, contact.email) &&
        Objects.equals(this.phoneNumber, contact.phoneNumber) &&
        Objects.equals(this.customerId, contact.customerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, firstName, lastName, email, phoneNumber, customerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contact {\n");
    
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
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

