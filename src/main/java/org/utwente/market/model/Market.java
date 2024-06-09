package org.utwente.market.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

import org.utwente.market.exceptions.BuyException;
import org.utwente.util.ValidationResult;

import java.util.ArrayList;
import lombok.Getter;

@Getter
public class Market {
    private static final int CURRENT_FULL_SIZE = 6;
    private Map<CardType, Integer> currentCards;
    private Map<CardType, Integer> reserveCards;

    public Market(List<CardTypeSpec> current, List<CardTypeSpec> reserve) {
        this.currentCards = current.stream()
                .collect(Collectors.toMap(CardTypeSpec::getType, CardTypeSpec::getQuantity));
        this.reserveCards = reserve.stream()
                .collect(Collectors.toMap(CardTypeSpec::getType, CardTypeSpec::getQuantity));

    }

    public Market() {
        this(MarketSetup.active.cardSpecification, MarketSetup.reserve.cardSpecification);
    }

    public ValidationResult canBuy(Order order) {
        if (!cardInReserve(order.getCardToken()) && !cardInCurrent(order.getCardToken())) {
            return new ValidationResult(false, "Card is not part of the market.");
        }
        if (!reserveIsOpen() && cardInReserve(order.getCardToken())) {
            return new ValidationResult(false, "Card is in reserve and reserve is closed.");
        }
        if (order.getMoney() < order.getCardToken().purchaseValue) {
            return new ValidationResult(false, "Cannot buy card with the given coins");
        }

        return new ValidationResult(true, null);
    }

    public boolean reserveIsOpen() {
        return Market.CURRENT_FULL_SIZE > this.currentCards.size();
    }

    public Card buy(Order order) throws BuyException {
        if (order == null) {
            throw new BuyException("Order is empty");
        }
        ValidationResult validationResult = canBuy(order);

        if (validationResult.getStatus() == false) {
            throw new BuyException(validationResult.getMessage());
        }

        removeCardFromMarket(order.getCardToken());
        return new Card(order.getCardToken());
    }

    public boolean cardInReserve(CardType type) {
        return this.reserveCards.containsKey(type);
    }

    public boolean cardInCurrent(CardType type) {
        return this.currentCards.containsKey(type);
    }

    public void removeCardFromMarket(CardType key) {
        if (cardInReserve(key)) {
            // move card to current cards.
            Integer cardAmount = this.reserveCards.get(key);
            this.currentCards.put(key, --cardAmount);
            this.reserveCards.remove(key);
        } else if (cardInCurrent(key)) {
            Integer cardAmount = this.currentCards.get(key);
            // card is given to the player
            this.currentCards.replace(key, --cardAmount);
            // remove card type that is empty.
            if (cardAmount <= 0) {
                this.currentCards.remove(key);
            }
        }
    }

    public int getRemainingCardAmount() {
        return Stream.of(currentCards, reserveCards)
                .flatMap(map -> map.values().stream())
                .mapToInt(Integer::intValue)
                .sum();

    }

    public List<CardType> getCurrent() {
        return new ArrayList<>(this.currentCards.keySet());
    }

    public List<CardType> getReserve() {
        return new ArrayList<>(this.reserveCards.keySet());
    }

    public Integer getRemainingAmount(CardType card) {
        if (this.cardInCurrent(card)) {
            return this.currentCards.get(card);
        }
        if (this.cardInReserve(card)) {
            return this.reserveCards.get(card);
        }

        return 0;
    }
}
