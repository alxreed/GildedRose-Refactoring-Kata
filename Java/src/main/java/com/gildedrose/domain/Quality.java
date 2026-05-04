package com.gildedrose.domain;

public record Quality(int value) {
    public static final int MAX = 50;

    public Quality {
        if (value < 0) {
            throw new IllegalArgumentException("Quality cannot be negative: " + value);
        }
    }

    public Quality increase(int n) {
        if (value >= MAX) {
            return this;
        } else {
            return new Quality(Math.min(MAX, value + n));
        }
    }

    public Quality decrease(int n) {
        return new Quality(Math.max(0, value - n));
    }

    public Quality reset() {
        return new Quality(0);
    }
}
