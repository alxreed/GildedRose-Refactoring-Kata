package com.gildedrose.domain.policy;

import com.gildedrose.domain.AgedState;

public final class LegendaryPolicy implements AgingPolicy {

    @Override
    public AgedState age(AgedState state) {
        return state;  // Sulfuras ne vieillit jamais et garde sa quality=80
    }
}
