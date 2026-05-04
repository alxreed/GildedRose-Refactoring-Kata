package com.gildedrose;

import com.gildedrose.domain.AgedState;
import com.gildedrose.domain.PolicyFactory;

import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        items.forEach(item -> {
            var policy = PolicyFactory.policyFor(item);
            var aged = policy.age(AgedState.of(item));
            aged.applyTo(item);
        });
    }
}

