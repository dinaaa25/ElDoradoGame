package org.utwente.CaveCoin;

import org.utwente.market.model.Resource;
import org.utwente.player.model.Pile;

import javax.swing.*;
import java.awt.*;

public class PlayCaveCoins extends JPanel {

    public PlayCaveCoins(Pile pile) {
        super(new FlowLayout());
        for (Resource caveCoin : pile.getResources()) {
            if (caveCoin instanceof CaveCoin) {
                this.add(new CaveCoinView((CaveCoin) caveCoin));
            }
        }
    }
}