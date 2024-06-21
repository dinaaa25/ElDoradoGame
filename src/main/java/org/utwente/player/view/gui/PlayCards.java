package org.utwente.player.view.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.utwente.market.model.Card;
import org.utwente.market.model.Resource;
import org.utwente.market.view.gui.CardComponent;
import org.utwente.player.model.Pile;

public class PlayCards extends JPanel {

  public PlayCards(Pile pile) {
    super(new FlowLayout());
    for (Resource card : pile.getResources()) {
      if (card instanceof Card) {
        this.add(new CardComponent((Card)card));
      }
    }
  }

}
