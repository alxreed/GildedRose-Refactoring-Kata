package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * One parameterized test per row in {@code src/test/resources/cases.csv}.
 *
 * Each row encodes an input item ({@code name}, {@code sellIn},
 * {@code quality}) and the expected state after a single
 * {@link GildedRose#updateQuality()} call. The CSV is generated once from
 * the original implementation by {@link GenerateCases} and treated as the
 * frozen oracle during refactoring.
 */
class UpdateQualityTest {

    @ParameterizedTest(name = "[{index}] {0} (sellIn={1}, quality={2}) -> sellIn={3}, quality={4}")
    @CsvFileSource(
            resources = "/cases.csv",
            numLinesToSkip = 1,
            delimiter = ';',
            quoteCharacter = '"'
    )
    void updateQuality_matchesExpectedBehaviour(
            String name,
            int sellIn,
            int quality,
            int expectedSellIn,
            int expectedQuality
    ) {
        // given an item built from the CSV row
        Item item = new Item(name, sellIn, quality);

        // when one day passes
        new GildedRose(List.of(item)).updateQuality();

        // then the resulting state matches the captured oracle
        assertThat(item.sellIn).as("sellIn").isEqualTo(expectedSellIn);
        assertThat(item.quality).as("quality").isEqualTo(expectedQuality);
    }
}
