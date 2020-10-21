package com.nttdata.quarkus.management.api.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;

@ApiModel(description = "Customer")
public class Customer {
    @JsonProperty("customerId")
    private BigDecimal customerId;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("address")
    private String address;

    @JsonProperty("website")
    private String website;

    @JsonProperty("creditLimit")
    private BigDecimal creditLimit;

    public Customer customerId(BigDecimal customerId) {
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

    public Customer fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Get fullName
     * @return fullName
     */
    @ApiModelProperty(value = "")


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    /**
     * Get address
     * @return address
     */
    @ApiModelProperty(value = "")


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer website(String website) {
        this.website = website;
        return this;
    }

    /**
     * Get website
     * @return website
     */
    @ApiModelProperty(value = "")


    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Customer creditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }

    /**
     * Get creditLimit
     * @return creditLimit
     */
    @ApiModelProperty(value = "")


    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

}
