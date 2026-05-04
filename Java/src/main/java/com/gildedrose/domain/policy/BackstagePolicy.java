package com.gildedrose.domain.policy;

import com.gildedrose.domain.AgedState;
import com.gildedrose.domain.Quality;
import com.gildedrose.domain.SellIn;

public final class BackstagePolicy implements AgingPolicy {

    private static final int LAST_CALL_THRESHOLD = 5;
    private static final int APPROACHING_THRESHOLD = 10;

    @Override
    public AgedState age(AgedState state) {
        var nextSellIn = state.sellIn().tick();
        var nextQuality = computeQuality(state.quality(), nextSellIn);
        return new AgedState(nextSellIn, nextQuality);
    }

    private Quality computeQuality(Quality current, SellIn sellIn) {
        if (sellIn.expired()) {
            return current.reset();
        } else if (sellIn.within(LAST_CALL_THRESHOLD)) {
            return current.increase(3);
        } else if (sellIn.within(APPROACHING_THRESHOLD)) {
            return current.increase(2);
        } else {
            return current.increase(1);
        }
    }
}
