package org.utwente.market.view;

import java.util.ArrayList;
import java.util.List;
import org.utwente.market.model.CardType;
import org.utwente.market.model.PowerType;
import org.utwente.util.Ansi;

public class CardFormat {

  private static final int cardHeight = 12;
  private static final int cardWidth = 16;
  private static final String cardBase = """
          ╭――――――――――――――――――╮
          │                  │
          │    %s │
          %s
          │  %s          %s  │
          │                  │
          │                  │
          │                  │
          │                  │
          │                  │
          │    %s coins      │
          ╰――――――――――――――――――╯
      """;

  public static String powerToColor(PowerType powerType) {
    if (powerType == PowerType.Machete) {
      return Ansi.GREEN_BOLD;
    }
    if (powerType == PowerType.Paddle) {
      return Ansi.BLUE_BOLD;
    }
    if (powerType == PowerType.Coin) {
      return Ansi.YELLOW_BOLD;
    }
    if (powerType == PowerType.Wild) {
      return Ansi.PURPLE_BOLD;
    }
    if (powerType == PowerType.Effect) {
      return Ansi.CYAN_BOLD_BRIGHT;
    }
    return "";
  }

  public static String formatCard(CardType card) {
    String name = card.toString();
    String empty = "│                  │";
    String underTitle = "│    ";
    String title = name;
    String preformat;
    // split name if too long.
    if (name.length() > 12) {
      String first = name.substring(0, 8);
      String second = name.substring(8, name.length());
      title = first;
      title += "-";
      underTitle += second;
      // it is necessary to do a pre formatting step to include the correct values of
      // card width.
      preformat = String.format(cardBase, "%s%-" + (cardWidth - 3) + "S%s", "%-" + 16 + "S%" + 4 + "s", "%s%2s",
          "%2s%s", "%2s");
      String color = powerToColor(card.powerType);
      return String.format(preformat, color, title, Ansi.RESET, underTitle, "│", color, card.power, card.power,
          Ansi.RESET, card.purchaseValue);
    } else {
      underTitle = empty;
      preformat = String.format(cardBase, "%s%-" + (cardWidth - 3) + "S%s", "%-" + 16 + "S", "%s%2s",
          "%2s%s", "%2s");
      String color = powerToColor(card.powerType);
      return String.format(preformat, color, title, Ansi.RESET, underTitle, color, card.power, card.power,
          Ansi.RESET, card.purchaseValue);
    }

  }

  public static String formatDeck(CardType[] cards) {
    StringBuilder result = new StringBuilder();
    List<String[]> cardLinesList = new ArrayList<>();
    int cardsPerRow = 4; // Number of resources per row
    int numRows = cardHeight; // Number of rows in each card

    for (CardType card : cards) {
      String formattedCard = formatCard(card);
      cardLinesList.add(formattedCard.split("\n"));
    }

    // Iterate over the resources in groups of cardsPerRow
    for (int k = 0; k < cards.length; k += cardsPerRow) {
      for (int j = 0; j < cardHeight; j++) {

        // Iterate over the resources in the current row
        for (int i = k; i < k + cardsPerRow && i < cards.length; i++) {
          // Append the line of the current card to the result
          result.append(" ");
          result.append(cardLinesList.get(i)[j]);
        }
        result.append("\n");
      }
      result.append("\n");
    }

    return result.toString();
  }

}
