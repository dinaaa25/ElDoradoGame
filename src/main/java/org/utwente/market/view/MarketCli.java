package org.utwente.market.view;

import java.util.Scanner;
import java.io.*;

import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Market;
import org.utwente.market.model.Order;
import org.utwente.util.Ansi;
import java.awt.event.*;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class MarketCli implements MarketView {
  private PrintStream stream;
  private ActionListener eventHandler;
  private ActionListener inputEventHandler;
  private Scanner scanner;
  private String orderInput;
  private Order order;
  private Market market;
  private boolean exit = false;

  public MarketCli(PrintStream stream, Scanner scanner) {
    this.stream = stream;
    this.scanner = new Scanner(System.in);
  }

  public MarketCli() {
    this(System.out, new Scanner(System.in));
  }

  public void run() {
    while (!exit) {
      String input = scanner.nextLine();

      if (input.equals("exit")) {
        exit();
      }
      if (input.equals("show")) {
        displayMarket();
      }
      if (input.startsWith("buy")) {
        String[] arguments = input.split(" ");

        try {
          // to title case:
          String token = String.valueOf(arguments[1].charAt(0)).toUpperCase() +
              arguments[1].substring(1);
          this.eventHandler.actionPerformed(new ActionEvent(this, 0, token));
        } catch (Exception e) {
          if (arguments.length >= 2) {
            displayError(String.format("%s is not a card in the game.",
                arguments[1]));
          } else {
            displayError("Please provide a card name to buy.");
          }
        }
      }
    }
  }

  @Override
  public Order getOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getOrder'");
  }

  @Override
  public void displayPurchaseResult(Card card) {
    String cardDisplay = CardFormat.formatCard(card.getCardType());
    stream.println(cardDisplay);
  }

  private void printStraightLine() {
    StringBuilder n = new StringBuilder();
    for (int i = 0; i < 100; i++)
      n.append("─");
    stream.println(n.toString());
  }

  @Override
  public void displayMarket() {
    String marketCurrentCards = CardFormat.formatDeck(market.getCurrentCardsSpec().keySet().toArray(CardType[]::new));
    String marketReserveCards = CardFormat.formatDeck(market.getReserveCardsSpec().keySet().toArray(CardType[]::new));
    stream.printf("\n%s%70s%30s%s\n\n", Ansi.BLUE_BACKGROUND, "welcome to el dorado market!", " ", Ansi.RESET);
    printStraightLine();
    stream.println("Market Available Cards:");
    stream.println(marketCurrentCards);
    printStraightLine();
    stream.println("Market Reserve Cards:");
    stream.println(marketReserveCards);
  }

  @Override
  public void displayError(String errorMessage) {
    stream.printf("%s%s%s\n", Ansi.RED, errorMessage, Ansi.RESET);
  }

  @Override
  public void setOnOrder(ActionListener eventHandler) {
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
  public void setOnInput(ActionListener eventHandler) {
    this.inputEventHandler = eventHandler;
  }

}
