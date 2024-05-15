package org.utwente.market.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardType {
    private String name;
    private int power;
    public String token;
    private int purchaseValue;
    private PowerType powerType;

    public CardType(String name, int power, String token, int purchaseValue, PowerType powerType) {
        this.name = name;
        this.power = power;
        this.token = token;
        this.purchaseValue = purchaseValue;
        this.powerType = powerType;
    }
}
