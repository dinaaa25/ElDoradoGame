package org.utwente.Tile;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class PowerRange {
    private final int minPower;
    private final int maxPower;

    public PowerRange(int minPower, int maxPower) {
        this.minPower = minPower;
        this.maxPower = maxPower;
    }

    public PowerRange(int power) {
        this(power, power);
    }

    public List<Integer> getRange() {
        return IntStream.rangeClosed(minPower, maxPower)
                .boxed()
                .collect(Collectors.toList());
    }
}
