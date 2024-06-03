package org.utwente.Board.Blockade;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class BlockadeController {
    private Blockade blockade;
    private BlockadeView blockadeView;

    public BlockadeController(Blockade blockade, BlockadeView blockadeView) {
        this.blockade = blockade;
        this.blockadeView = blockadeView;
    }

    public void updateView(Graphics2D g2d, int yOffset) {
        blockadeView.draw(g2d, blockade, yOffset);
    }
}
