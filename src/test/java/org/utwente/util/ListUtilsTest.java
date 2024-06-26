package org.utwente.util;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.utwente.util.ListUtils.splitListIntoChunks;

public class ListUtilsTest {
    @Test
    public void testListOfIntegers() {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        List<List<Integer>> chunkedList = splitListIntoChunks(list, 4);
        assertEquals(4, chunkedList.size());
    }

    @Test
    public void testListOfCaveCoins() {
        List<CaveCoin> caveCoinList = CaveCoinLoader.loadCoins();
        List<List<CaveCoin>> chunkedList = splitListIntoChunks(caveCoinList, 4);
        assertEquals(9, chunkedList.size());
        for (List<CaveCoin> chunk : chunkedList) {
            assertEquals(4, chunk.size());
        }
    }
}