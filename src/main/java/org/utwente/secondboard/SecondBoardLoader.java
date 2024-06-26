package org.utwente.secondboard;


import org.utwente.Board.Board;
import org.utwente.Section.Section;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.secondboard.boardPieces.HexagonGameBoard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SecondBoardLoader {

    Tile newTileToOGTile(org.utwente.secondboard.boardPieces.Tile tile) {
        Tile newTile = new Tile(tile.getQ(), tile.getR(), colorToTileType(tile.getColor()), tile.getPoints(), null, false);
        return newTile;
    }

    TileType colorToTileType(Color color) {
        if (Objects.equals(color, new Color(0, 255, 0)))
        {
            return TileType.Machete;
        }
        return TileType.Paddle;
    }

    public Board getConvertedBoard() {
        HexagonGameBoard board = new HexagonGameBoard(28, 28, 30,false);
        List<Tile> tiles = new ArrayList<>();
        for (org.utwente.secondboard.boardPieces.Tile tile : board.boardPieces.get("Terrain_1").getTiles()) {
            tiles.add(newTileToOGTile(tile));
        }
        Section section = new Section(tiles, SectionType.A);
        Board board2 = new Board(List.of(section), null, true, List.of());
        System.out.println(board2);
        return setStartingTiles(board2);
    }

    public Board setStartingTiles(Board board) {
        int count = 0;
        for (Section section : board.getSections()) {
            for (Tile tile : section.getTiles()) {
                if (count > 4) return board;
                tile.setTileType(TileType.Start);
                count++;
            }
        }
        return board;
    }

    public static void main(String[] args) {
        SecondBoardLoader secondBoardLoader = new SecondBoardLoader();
        Board board = secondBoardLoader.getConvertedBoard();
        System.out.println(board);
    }
}
