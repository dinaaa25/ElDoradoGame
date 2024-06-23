package org.utwente.CaveCoin;

import org.utwente.player.model.CoinPile;
import javax.swing.*;
import java.awt.*;

public class PlayCaveCoins extends JPanel {

    public PlayCaveCoins(CoinPile pile) {
        super(new FlowLayout());
        for (CaveCoin caveCoin : pile.getResources()) {
            this.add(new CaveCoinView((CaveCoin) caveCoin));
        }
    }
}