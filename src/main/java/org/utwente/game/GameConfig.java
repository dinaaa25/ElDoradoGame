package org.utwente.game;

import org.utwente.Tile.TileType;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class GameConfig {
    public static final Font TILE_FONT = new Font("Arial", Font.BOLD, 12);;
    public static final int DEFAULT_BOARD_SIZE_X = 600;
    public static final int DEFAULT_BOARD_SIZE_Y = 800;
    public static final int PADDING = 50;

    public static final Map<TileType, Color> TILE_COLORS = new EnumMap<>(TileType.class);
    static {
        TILE_COLORS.put(TileType.Machete, new Color(101, 140, 35));
        TILE_COLORS.put(TileType.Paddle, new Color(107, 194, 235));
        TILE_COLORS.put(TileType.Coin, new Color(241, 215, 95));
        TILE_COLORS.put(TileType.Basecamp, new Color(203, 85, 35));
        TILE_COLORS.put(TileType.Mountain, new Color(106, 100, 74));
        TILE_COLORS.put(TileType.Cave, new Color(224, 159, 65));
    }

    public static final int HEX_SIZE = 40;
}
