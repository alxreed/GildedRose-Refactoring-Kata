package com.gildedrose.domain.policy;

import com.gildedrose.domain.AgedState;

public final class ConjuredPolicy implements AgingPolicy {

    @Override
    public AgedState age(AgedState state) {
        var nextSellIn = state.sellIn().tick();
        var nextQuality = state.quality().decrease(nextSellIn.expired() ? 4 : 2);
        return new AgedState(nextSellIn, nextQuality);
    }
}
