package org.utwente.game.model;

import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventType;

public class CaveCoinDrawEffectPhase extends EffectPhase {
    public CaveCoinDrawEffectPhase(Resource resource, Player player) {
        super(resource, player);
    }

    @Override
    protected void defineSteps() {
        this.createMandatoryStep(EventType.CaveCoinDraw, 0, "Draw cards", "You can draw " + this.getResource().getPower() + " cards.");
    }
}
