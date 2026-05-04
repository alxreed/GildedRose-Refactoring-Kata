package com.gildedrose.domain;

public record SellIn(int days) {

    public SellIn tick() {
        return new SellIn(days - 1);
    }

    public boolean expired() {
        return days < 0;
    }

    public boolean within(int threshold) {
        return days < threshold;
    }
}
