package org.utwente.market.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.utwente.market.model.configuration.MarketCardConfig;
import org.utwente.market.model.configuration.MarketConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Market {

    private Map<CardType, Integer> currentCards;
    private Map<CardType, Integer> reserveCards;

    public Market() {
        this.currentCards = new HashMap<>();
        this.reserveCards = new HashMap<>();
    }

    static PowerType mapTypeToEnum(String type) {
        if (type.equals("machete")) {
            return PowerType.Machete;
        }
        if (type.equals("paddle")) {
            return PowerType.Paddle;
        }
        if (type.equals("coin")) {
            return PowerType.Coin;
        }
        if (type.equals("wild")) {
            return PowerType.Wild;
        }
        return null;
    }

    public static Market fromConfiguration() {
        Market market = new Market();
        String path = "src/main/resources/market.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON file and map it to an array of custom objects
            MarketConfig marketConfiguration = objectMapper.readValue(new File(path), MarketConfig.class);

            List<MarketCardConfig> startingAvailableCards = marketConfiguration.getAvailable();
            List<MarketCardConfig> startingReserveCards = marketConfiguration.getReserve();

            for (MarketCardConfig cardConfig : startingAvailableCards) {
                CardType card = new CardType(cardConfig.getName(), cardConfig.getPower(),
                        cardConfig.getName(),
                        cardConfig.getPrice(), Market.mapTypeToEnum(cardConfig.getPowerType()));
                market.currentCards.put(card, cardConfig.getQuantity());
            }

            for (MarketCardConfig cardConfig : startingReserveCards) {
                CardType card = new CardType(cardConfig.getName(), cardConfig.getPower(),
                        cardConfig.getName(),
                        cardConfig.getPrice(), Market.mapTypeToEnum(cardConfig.getPowerType()));
                market.reserveCards.put(card, cardConfig.getQuantity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return market;
    }

    public boolean canBuy(Order order) {
        return false;
    }

    public Card buy(Order order) {
        return null;
    }
}
