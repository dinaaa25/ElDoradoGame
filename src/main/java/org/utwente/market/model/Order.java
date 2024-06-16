package org.utwente.market.model;

public class Order {

    /**
     * the type of card you want to buy
     */
    private CardType cardType;

    /**
     * amount of money you want to spend
     */
    private int money;

    public Order(CardType cardType, int money) {
        this.cardType = cardType;
        this.money = money;
    }

    public CardType getCardToken() {
        return this.cardType;
    }

    public int getMoney() {
        return this.money;
    }
}
