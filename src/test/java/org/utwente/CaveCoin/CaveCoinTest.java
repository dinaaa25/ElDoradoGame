package org.utwente.CaveCoin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaveCoinTest {
    List<CaveCoin> caveCoins;

    @BeforeEach
    void init() {
        caveCoins = CaveCoinLoader.loadCoins();
    }

    @Test
    void testAmountOfCoins() {
        assertEquals(36, caveCoins.size());
    }

    @Test
    void testDistributionOfMacheteCoins() {
        List<CaveCoin> macheteCoins = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Machete)
                .toList();
        assertEquals(7, macheteCoins.size());

        List<CaveCoin> macheteCoinsPower1 = macheteCoins.stream().filter(coin -> coin.power() == 1).toList();
        assertEquals(2, macheteCoinsPower1.size());

        List<CaveCoin> macheteCoinsPower2 = macheteCoins.stream().filter(coin -> coin.power() == 2).toList();
        assertEquals(3, macheteCoinsPower2.size());

        List<CaveCoin> macheteCoinsPower3 = macheteCoins.stream().filter(coin -> coin.power() == 3).toList();
        assertEquals(2, macheteCoinsPower3.size());
    }

    @Test
    void testDistributionOfPaddleCoins() {
        List<CaveCoin> paddleCoins = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Paddle)
                .toList();
        assertEquals(5, paddleCoins.size());

        List<CaveCoin> macheteCoinsPower1 = paddleCoins.stream().filter(coin -> coin.power() == 1).toList();
        assertEquals(2, macheteCoinsPower1.size());

        List<CaveCoin> macheteCoinsPower2 = paddleCoins.stream().filter(coin -> coin.power() == 2).toList();
        assertEquals(3, macheteCoinsPower2.size());
    }

    @Test
    void testDistributionOfCoinCoins() {
        List<CaveCoin> coinCoins = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Coin)
                .toList();
        assertEquals(5, coinCoins.size());

        List<CaveCoin> macheteCoinsPower1 = coinCoins.stream().filter(coin -> coin.power() == 1).toList();
        assertEquals(2, macheteCoinsPower1.size());

        List<CaveCoin> macheteCoinsPower2 = coinCoins.stream().filter(coin -> coin.power() == 2).toList();
        assertEquals(3, macheteCoinsPower2.size());
    }

    @Test
    void testAmountOfDrawCoins() {
        long count = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Draw)
                .count();
        assertEquals(4, count, "There should be 4 Draw coins");
    }

    @Test
    void testAmountOfRemoveCoins() {
        long count = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Remove)
                .count();
        assertEquals(4, count, "There should be 4 Remove coins");
    }

    @Test
    void testAmountOfReplaceCoins() {
        long count = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Replace)
                .count();
        assertEquals(3, count, "There should be 3 Replace coins");
    }

    @Test
    void testAmountOfDontRemoveCoins() {
        long count = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.DontRemove)
                .count();
        assertEquals(2, count, "There should be 2 DontRemove coins");
    }

    @Test
    void testAmountOfPassThroughCoins() {
        long count = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.PassThrough)
                .count();
        assertEquals(2, count, "There should be 2 PassThrough coins");
    }

    @Test
    void testAmountOfAdjacentCoins() {
        long count = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Adjacent)
                .count();
        assertEquals(2, count, "There should be 2 Adjacent coins");
    }

    @Test
    void testAmountOfSymbolCoins() {
        long count = caveCoins.stream()
                .filter(coin -> coin.getCaveCoinType() == CaveCoinType.Symbol)
                .count();
        assertEquals(2, count, "There should be 2 Symbol coins");
    }
}
