package com.gildedrose.domain.policy;

import com.gildedrose.domain.AgedState;

public final class BriePolicy implements AgingPolicy {

    @Override
    public AgedState age(AgedState state) {
        var nextSellIn = state.sellIn().tick();
        var nextQuality = state.quality().increase(nextSellIn.expired() ? 2 : 1);
        return new AgedState(nextSellIn, nextQuality);
    }
}
