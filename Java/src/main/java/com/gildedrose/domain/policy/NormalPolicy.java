package com.gildedrose.domain.policy;

import com.gildedrose.domain.AgedState;

public final class NormalPolicy implements AgingPolicy {

    @Override
    public AgedState age(AgedState state) {
        var nextSellIn = state.sellIn().tick();
        var nextQuality = state.quality().decrease(nextSellIn.expired() ? 2 : 1);
        return new AgedState(nextSellIn, nextQuality);
    }
}
