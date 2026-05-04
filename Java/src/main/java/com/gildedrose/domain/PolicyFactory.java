package com.gildedrose.domain;

import com.gildedrose.Item;
import com.gildedrose.domain.policy.*;

public final class PolicyFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED_PREFIX = "Conjured";

    private PolicyFactory() {
    }

    public static AgingPolicy policyFor(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> new BriePolicy();
            case SULFURAS -> new LegendaryPolicy();
            case BACKSTAGE -> new BackstagePolicy();
            case String n when n.startsWith(CONJURED_PREFIX) -> new ConjuredPolicy();
            default -> new NormalPolicy();
        };
    }
}
