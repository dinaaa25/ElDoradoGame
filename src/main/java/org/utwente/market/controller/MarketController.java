package org.utwente.market.controller;

import org.utwente.market.exceptions.BuyException;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import org.utwente.market.view.MarketView;

import java.awt.event.*;

public class MarketController {
  private MarketView view;
  private Market model;

  public MarketController(MarketView view, Market model) {
    this.view = view;
    this.model = model;
    this.view.setMarket(model);
    this.view.setOnOrder(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          Card card = model.buy(new Order(CardType.valueOf(e.getActionCommand()), 3));
          view.displayPurchaseResult(card);
          view.setMarket(model);
          view.displayMarket();
        } catch (BuyException exception) {
          view.displayError(exception.getMessage());
        }
      }

    });

    // this.view.setOnInput(new EventHandler<InputEvent>() {
    // @Override
    // public void handle(InputEvent event) {
    // if (event.getInput().equals("exit")) {
    // view.exit();
    // }
    // if (event.getInput().equals("show")) {
    // view.displayMarket();
    // }
    // if (event.getInput().startsWith("buy")) {
    // String[] arguments = event.getInput().split(" ");

    // try {
    // // to title case:
    // String token = String.valueOf(arguments[1].charAt(0)).toUpperCase() +
    // arguments[1].substring(1);
    // CardType desiredCard = CardType.valueOf(token);
    // Order order = new Order(desiredCard, 5);
    // if (model.canBuy(order)) {
    // Card card = model.buy(order);
    // view.displayPurchaseResult(card);
    // } else {
    // view.displayError(String.format("cannot buy card: %s", desiredCard));
    // }
    // } catch (Exception e) {
    // if (arguments.length >= 2) {
    // view.displayError(String.format("%s is not a card in the game.",
    // arguments[1]));
    // } else {
    // view.displayError("Please provide a card name to buy.");
    // }
    // }

    // }
    // }
    // });
  }

}
