package org.utwente;

import org.utwente.market.controller.MarketController;
import org.utwente.market.model.Market;
import org.utwente.market.view.MarketCli;

public class Main {

    public static void bootstrap() {
        Market market = new Market();
        MarketCli view = new MarketCli();
        MarketController controller = new MarketController(view, market);
        view.run();
    }

    public static void main(String[] args) {
        bootstrap();
    }
}