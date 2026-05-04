package com.gildedrose.domain;

import com.gildedrose.Item;

public record AgedState(SellIn sellIn, Quality quality) {

    public static AgedState of(Item item) {
        return new AgedState(new SellIn(item.sellIn), new Quality(item.quality));
    }

    public AgedState withSellIn(SellIn newSellIn) {
        return new AgedState(newSellIn, quality);
    }

    public AgedState withQuality(Quality newQuality) {
        return new AgedState(sellIn, newQuality);
    }

    public void applyTo(Item item) {
        item.sellIn = sellIn.days();
        item.quality = quality.value();
    }
}
