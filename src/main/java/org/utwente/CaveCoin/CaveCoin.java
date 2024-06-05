package org.utwente.CaveCoin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CaveCoin(int power, CaveCoinType caveCoinType) {
    @JsonCreator
    public CaveCoin(@JsonProperty("power") int power,
                    @JsonProperty("caveCoinType") CaveCoinType caveCoinType) {
        this.power = power;
        this.caveCoinType = caveCoinType;
    }
}
