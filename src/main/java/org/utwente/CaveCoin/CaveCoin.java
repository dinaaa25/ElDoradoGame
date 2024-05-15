package org.utwente.CaveCoin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CaveCoin {
    private final int power;
    private final CaveCoinType caveCoinType;

    @JsonCreator
    public CaveCoin(@JsonProperty("power") int power,
                    @JsonProperty("caveCoinType") CaveCoinType caveCoinType) {
        this.power = power;
        this.caveCoinType = caveCoinType;
    }

    public int getPower() {
        return power;
    }

    public CaveCoinType getCaveCoinType() {
        return caveCoinType;
    }
}
