package org.utwente.secondboard.boardPieces;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HexagonGameBoard extends JPanel {
    public int numRows;
    public int numCols;
    public int hexSize;
    public int cardWidth;
    public int cardHeight;
    public Map<String, Tile> ParentMap;
    public Map<String, BoardPiece> boardPieces;
    public List<double[]> coordinateList;
    public boolean setupChange;
    public HexagonGameBoard(int numRows, int numCols, int hexSize,boolean setupChange) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.hexSize = hexSize;
        this.cardWidth = hexSize * 2;
        this.cardHeight = cardWidth / 2 * 3;
        this.setupChange=setupChange;
        setPreferredSize(new Dimension((int) (numCols * 1.5 * hexSize), (int) (numRows * Math.sqrt(3) * hexSize)));

        ParentMap = new HashMap<>();
        boardPieces = new HashMap<>();
        Terrain.resetWinningCount();
        Blockade.resetCount();
        WinningPiece.resetCount();
        coordinateList = new ArrayList<>(List.of(
                new double[]{6, 4},
                new double[]{0, 8},
                new double[]{-6, 4}
        ));
        loadTileData();
        initBoard();
    }

    public void initBoard() {
        for (double[] coordinates : coordinateList) {
            Terrain terrainLatest=getLastTerrain();
            addTerrain(coordinates[0], coordinates[1], terrainLatest);
        }
        
    }
    

    public void loadTileData() {
        TileDataDic tdd = new TileDataDic(numRows, numCols, hexSize);
        boardPieces.put(tdd.terrainA.getName(), tdd.terrainA);
        boardPieces.put(tdd.wpa.getName(), tdd.wpa);
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (BoardPiece piece : boardPieces.values()) {
            for (Tile tile : piece.getTiles()) {
                ParentMap.put(tile.getRow() + "," + tile.getCol(), tile);
            }
            piece.draw(g2d, hexSize);
        }

        

    }

    
    public Terrain getLastTerrain() {
    	 int maxIndex = 0;
         List<Terrain> terrains = getAllTerrains();
         
         for (Terrain terrain : terrains) {
             String name = terrain.getName();
             int index = Integer.parseInt(name.substring("Terrain_".length()));

             // Check if this index is greater than the current maxIndex
             if (index > maxIndex) {
                 maxIndex = index;
             }

         }
    	return (Terrain) boardPieces.get("Terrain_"+maxIndex);
    }
    public Terrain getFirstTerrain() {
   	 int minIndex = 100;
        List<Terrain> terrains = getAllTerrains();
        
        for (Terrain terrain : terrains) {
            String name = terrain.getName();
            int index = Integer.parseInt(name.substring("Terrain_".length()));

            // Check if this index is greater than the current maxIndex
            if (index < minIndex) {
            	minIndex = index;
            }

        }
   	return (Terrain) boardPieces.get("Terrain_"+minIndex);
   }
    public WinningPiece getLastWinningPiece() {
   	 int maxIndex = 0;
        List<WinningPiece> WinningPieces = getAllWinningPieces();
        
        for (WinningPiece wp : WinningPieces) {
            String name = wp.getName();
            int index = Integer.parseInt(name.substring("Winning_".length()));

            // Check if this index is greater than the current maxIndex
            if (index > maxIndex) {
                maxIndex = index;
            }

        }
   	return (WinningPiece) boardPieces.get("Winning_"+maxIndex);
   }
    public List<Terrain> getAllTerrains() {
        return boardPieces.keySet().stream()
                .filter(key -> key.startsWith("Terrain_"))
                .map(key -> (Terrain) boardPieces.get(key))
                .collect(Collectors.toList());
    }

    public List<Blockade> getAllBlockades() {
        return boardPieces.keySet().stream()
                .filter(key -> key.startsWith("Blockade_"))
                .map(key -> (Blockade) boardPieces.get(key))
                .collect(Collectors.toList());
    }

    public List<WinningPiece> getAllWinningPieces() {
        return boardPieces.keySet().stream()
                .filter(key -> key.startsWith("Winning_"))
                .map(key -> (WinningPiece) boardPieces.get(key))
                .collect(Collectors.toList());
    }
 
    public List<Tile> findStarterTiles(){
    	List<Tile> starterTiles = new ArrayList<>();
    	Color targetColor=new Color(0,100,0);
    	for (Tile tile : getFirstTerrain().getTiles()) {
    	    if (tile.getColor().equals(targetColor)) {
    	    	starterTiles.add(tile);
    	    }
    	} return starterTiles;
    }
    
    public void addTerrain(double addRow, double addCol, Terrain terrainA) {
        addWinningPiece(addRow, addCol, getLastWinningPiece());
        Terrain terrainB = terrainA.clone(addRow, addCol,hexSize);
        terrainB.randomizeTiles();
        boardPieces.put(terrainB.getName(), terrainB);
        Set<int[]> neighbors = terrainA.findOverlappingNeighbors(terrainB);
        if (neighbors.size() >= 3 && neighbors.size() <= 5) {
            Blockade blockade = new Blockade();

            for (int[] coordinate : neighbors) {
                int row = coordinate[0];
                int col = coordinate[1];
                Tile temp = TileDataDic.tilesMap.get(row + "," + col);
                blockade.addTile(temp);
            }

            blockade.randomizeTiles();
            int indexA = Integer.parseInt(terrainA.getName().substring("Terrain_".length()));
            int indexB = Integer.parseInt(terrainB.getName().substring("Terrain_".length()));
            blockade.setTerrainNeighbors(indexA, indexB);
            boardPieces.put(blockade.getName(), blockade);
        }
    }

    public void addWinningPiece(double addRow, double addCol, WinningPiece wpa) {
        WinningPiece wpb = wpa.clone(addRow, addCol,hexSize);

        for (Tile tile : wpa.getTiles()) {
            tile.setParent(null);
        }

        boardPieces.remove(wpa.getName());
        boardPieces.put(wpb.getName(), wpb);
    }

    public void removeBlockade(int blockRemoveIndex) {
        Blockade blockRemove = (Blockade) boardPieces.get("Blockade_" + (blockRemoveIndex));
        int indexTerrain = blockRemove.getTerrainNeighbors()[0];
        boardPieces.remove("Blockade_" + (blockRemoveIndex));
        double[] change;

        if (coordinateList.get(indexTerrain - 1)[0] > 0) {
            change = new double[] { -1, 0 }; // Move one unit up
        } else if (coordinateList.get(indexTerrain - 1)[0] < 0) {
            change = new double[] { 1, 0 }; // Move one unit down
        } else {
            change = new double[] { -0.5, -0.5 }; // Move one unit left
        }

        for (BoardPiece piece : boardPieces.values()) {
            if (piece.getName().startsWith("Terrain_")) {
                String indexString = piece.getName().substring("Terrain_".length()); 
                int index = Integer.parseInt(indexString);
                if (index <= indexTerrain) {
                    continue;
                }
            }
            piece.move(change[0], change[1],hexSize);
        }
    }

    
}