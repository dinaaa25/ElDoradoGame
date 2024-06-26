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
        } else if (Objects.equals(color, new Color(0, 100, 0))) {
            return TileType.Start;
        } else if (Objects.equals(color, Color.GRAY)) {
            return TileType.Discard;
        } else if (Objects.equals(color, Color.YELLOW)) {
            return TileType.Coin;
        } else if (Objects.equals(color, Color.CYAN)) {
            return TileType.Paddle;
        }
        return TileType.Paddle;
    }

    public Board getConvertedBoard() {
        HexagonGameBoard board = new HexagonGameBoard(28, 28, 30,false);
        List<Tile> tilesSection1 = new ArrayList<>();
        List<Tile> tilesSection2 = new ArrayList<>();
        List<Tile> tilesSection3 = new ArrayList<>();
        List<Tile> tilesSection4 = new ArrayList<>();
        for (org.utwente.secondboard.boardPieces.Tile tile : board.boardPieces.get("Terrain_1").getTiles()) {
            tilesSection1.add(newTileToOGTile(tile));
        }
        for (org.utwente.secondboard.boardPieces.Tile tile : board.boardPieces.get("Terrain_2").getTiles()) {
            tilesSection2.add(newTileToOGTile(tile));
        }
        for (org.utwente.secondboard.boardPieces.Tile tile : board.boardPieces.get("Terrain_3").getTiles()) {
            tilesSection3.add(newTileToOGTile(tile));
        }
        for (org.utwente.secondboard.boardPieces.Tile tile : board.boardPieces.get("Terrain_4").getTiles()) {
            tilesSection4.add(newTileToOGTile(tile));
        }
        Section section1 = new Section(tilesSection1, SectionType.A);
        Section section2 = new Section(tilesSection2, SectionType.A);
        Section section3 = new Section(tilesSection3, SectionType.A);
        Section section4 = new Section(tilesSection4, SectionType.A);
        Board board2 = new Board(List.of(section1, section2, section3, section4), null, true, List.of());
        System.out.println(board2);
        return board2;
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
