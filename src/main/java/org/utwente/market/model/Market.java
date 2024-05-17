package org.utwente.market.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Market {

    private Map<CardType, Integer> currentCards;
    private Map<CardType, Integer> reserveCards;

    public Market(List<CardTypeSpec> current, List<CardTypeSpec> reserve) {
        this.currentCards = new HashMap<>();
        this.reserveCards = new HashMap<>();

        for (CardTypeSpec currentCardTypeSpec : current) {
            this.currentCards.put(currentCardTypeSpec.getType(), currentCardTypeSpec.getQuantity());
        }

        for (CardTypeSpec reserveCardTypeSpec : reserve) {
            this.currentCards.put(reserveCardTypeSpec.getType(), reserveCardTypeSpec.getQuantity());
        }
    }

    public Market() {
        this(MarketSetup.active.cardSpecification, MarketSetup.reserve.cardSpecification);
    }

    public boolean canBuy(Order order) {
        return false;
    }

    public Card buy(Order order) {
        return null;
    }
}
