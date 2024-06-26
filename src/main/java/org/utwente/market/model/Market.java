package org.utwente.market.model;

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
    private Map<CardType, Integer> currentCardsSpec;
    private Map<CardType, Integer> reserveCardsSpec;

    public Market(List<CardTypeSpec> current, List<CardTypeSpec> reserve) {
        this.currentCardsSpec = current.stream()
                .collect(Collectors.toMap(CardTypeSpec::getType, CardTypeSpec::getQuantity));
        this.reserveCardsSpec = reserve.stream()
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

    public boolean isCardAvailable(CardType cardType) {
        return cardInCurrent(cardType) || cardInReserve(cardType);
    }

    public boolean reserveIsOpen() {
        return Market.CURRENT_FULL_SIZE > this.currentCardsSpec.size();
    }

    /**
     * buy card via order which includes card type and money you have
     * @param order
     * @return
     * @throws BuyException
     */
    public Card buy(Order order) throws BuyException {
        if (order == null) {
            throw new BuyException("Order is empty");
        }
        ValidationResult validationResult = canBuy(order);

        if (!validationResult.getStatus()) {
            throw new BuyException(validationResult.getMessage());
        }

        return buy(order.getCardToken());
    }

    /**
     * buy card via specifying only the card type
     * you do not need any money since you use the transmitter card as resource to buy anything
     * @param type
     * @return
     * @throws BuyException
     */
    public Card buy(CardType type) throws BuyException {
        removeCardFromMarket(type);
        return new Card(type);
    }

    public boolean cardInReserve(CardType type) {
        return this.reserveCardsSpec.containsKey(type);
    }

    public boolean cardInCurrent(CardType type) {
        return this.currentCardsSpec.containsKey(type);
    }

    public void removeCardFromMarket(CardType key) {
        if (cardInReserve(key)) {
            // move card to current resources.
            Integer cardAmount = this.reserveCardsSpec.get(key);
            this.currentCardsSpec.put(key, --cardAmount);
            this.reserveCardsSpec.remove(key);
        } else if (cardInCurrent(key)) {
            Integer cardAmount = this.currentCardsSpec.get(key);
            // card is given to the player
            this.currentCardsSpec.replace(key, --cardAmount);
            // remove card type that is empty.
            if (cardAmount <= 0) {
                this.currentCardsSpec.remove(key);
            }
        }
    }

    public int getRemainingCardAmount() {
        return Stream.of(currentCardsSpec, reserveCardsSpec)
                .flatMap(map -> map.values().stream())
                .mapToInt(Integer::intValue)
                .sum();

    }

    public List<CardType> getCurrent() {
        return new ArrayList<>(this.currentCardsSpec.keySet());
    }

    public List<CardType> getReserve() {
        return new ArrayList<>(this.reserveCardsSpec.keySet());
    }

    public Integer getRemainingAmount(CardType card) {
        if (this.cardInCurrent(card)) {
            return this.currentCardsSpec.get(card);
        }
        if (this.cardInReserve(card)) {
            return this.reserveCardsSpec.get(card);
        }

        return 0;
    }
}
