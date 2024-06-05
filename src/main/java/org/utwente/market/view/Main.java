package org.utwente.market.view;

import java.util.Scanner;

import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("use GUI y/n ?");
    String response = scanner.nextLine();
    MarketView view;
    if (response.contains("n")) {
      view = new MarketCli();
    } else {
      view = new MarketGui();
    }
    Market market = new Market();
    MarketController marketController = new MarketController(view, market);
    view.run();
  }
}
