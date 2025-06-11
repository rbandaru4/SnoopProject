package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
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