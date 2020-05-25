package com.example.datavalidationdemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@JsonPropertyOrder({"validationValue", "sensorIdentifier"})
public class DataDto {

    private int validationValue;
    private String sensorIdentifier;

    @JsonCreator
    public DataDto(
            @JsonProperty("validationValue") int validationValue,
            @JsonProperty("sensorIdentifier") String sensorIdentifier) {
        this.validationValue = validationValue;
        this.sensorIdentifier = sensorIdentifier;
    }
}
