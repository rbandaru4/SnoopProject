package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.time.OffsetDateTime;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("merchantName")
    private String merchantName;

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime timestamp;

    @JsonProperty("type")
    private String type;

    @JsonProperty("subType")
    private String subType;

    @JsonProperty("status")
    private String status;

    @JsonProperty("categoryId")
    private Integer categoryId;

    @JsonProperty("description")
    private String description;
}