package com.gildedrose.domain.policy;

import com.gildedrose.domain.AgedState;

public sealed interface AgingPolicy
    permits NormalPolicy, BriePolicy, BackstagePolicy, ConjuredPolicy, LegendaryPolicy {

    AgedState age(AgedState state);
}
