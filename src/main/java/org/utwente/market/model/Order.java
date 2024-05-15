package org.utwente.market.model;

public class Order {

    private String cardToken;
    private int money;

    public Order(String cardToken, int money) {
        this.cardToken = cardToken;
        this.money = money;
    }

    public String getCardToken() {
        return this.cardToken;
    }

    public int getMoney() {
        return this.money;
    }
}
