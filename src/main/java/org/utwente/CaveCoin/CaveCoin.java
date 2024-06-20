package org.utwente.CaveCoin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.utwente.market.model.CardPowerException;
import org.utwente.market.model.PowerType;
import org.utwente.market.model.Resource;
import java.util.*;

public class CaveCoin implements Resource {
    int power;
    int removedPower = 0;
    CaveCoinType caveCoinType;

    @JsonCreator
    public CaveCoin(@JsonProperty("power") int power,
            @JsonProperty("caveCoinType") CaveCoinType caveCoinType) {
        this.power = power;
        this.caveCoinType = caveCoinType;
    }

    public int power() {
        return power;
    }

    public CaveCoinType caveCoinType() {
        return this.caveCoinType;
    }

    @Override
    public int getPower() {
        return this.remainingPower();
    }

    private Map<CaveCoinType, PowerType> getPowerMap() {
        Map<CaveCoinType, PowerType> map = new HashMap<>();
        map.put(CaveCoinType.Coin, PowerType.Coin);
        map.put(CaveCoinType.Paddle, PowerType.Paddle);
        map.put(CaveCoinType.Machete, PowerType.Machete);

        return map;
    }

    @Override
    public PowerType getType() {
        Map<CaveCoinType, PowerType> map = getPowerMap();
        return map.containsKey(this.caveCoinType) ? map.get(this.caveCoinType) : PowerType.Effect;
    }

    @Override
    public double getValue() {
        if (this.caveCoinType == CaveCoinType.Coin) {
            return this.power;
        }
        return 0;
    }

    public int remainingPower() {
        return power - removedPower;
    }

    @Override
    public void removePower(int toBeRemoved) throws CardPowerException {
        if (toBeRemoved > remainingPower()) {
            throw new CardPowerException("Not enough power to remove.");
        }
        this.removedPower += toBeRemoved;
    }
}
