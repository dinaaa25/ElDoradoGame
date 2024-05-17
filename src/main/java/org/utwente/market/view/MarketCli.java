package org.utwente.market.view;

import java.util.Scanner;

import org.utwente.market.controller.InputEvent;
import org.utwente.market.controller.MarketOrderEvent;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import org.utwente.util.Ansi;
import org.utwente.util.EventHandler;

public class MarketCli implements MarketView {
  private EventHandler<MarketOrderEvent> eventHandler;
  private EventHandler<InputEvent> inputEventHandler;
  private Scanner scanner = new Scanner(System.in);
  private String orderInput;
  private Order order;
  private Market market;
  private boolean exit = false;

  public void run() {
    while (!exit) {
      String input = scanner.nextLine();
      inputEventHandler.handle(new InputEvent(input));
    }
  }

  @Override
  public Order getOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
  }

  @Override
  public void displayPurchaseResult() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'displayPurchaseResult'");
  }

  @Override
  public void displayMarket() {
    String marketRepresentation = CardFormat.formatDeck(market.getCurrentCards().keySet().toArray(CardType[]::new));
    System.out.printf("\n%s%70s%30s%s\n\n", Ansi.BLUE_BACKGROUND, "welcome to el dorado market!", " ", Ansi.RESET);
    System.out.println(marketRepresentation);
  }

  @Override
  public void displayError(String errorMessage) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'displayError'");
  }

  @Override
  public void setOnOrder(EventHandler<MarketOrderEvent> eventHandler) {
    this.eventHandler = eventHandler;
  }

  @Override
  public void setMarket(Market market) {
    this.market = market;
  }

  @Override
  public void exit() {
    this.exit = true;
  }

  @Override
  public void setOnInput(EventHandler<InputEvent> eventHandler) {
    this.inputEventHandler = eventHandler;
  }

}
