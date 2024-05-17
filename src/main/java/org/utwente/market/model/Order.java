package org.utwente.market.model;

public class Order {

    private CardType cardType;
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
