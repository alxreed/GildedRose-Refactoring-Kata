package com.gildedrose;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * One-shot generator for the parameterized test data set.
 *
 * Iterates over a deterministic catalogue of items, runs a single
 * {@code updateQuality()} call on each, and writes the captured (input ->
 * expected output) pairs to a CSV file consumed by both the Java and Dart
 * parameterized tests.
 *
 * Run once before refactoring to capture the current behaviour:
 *   ./mvnw -B test-compile
 *   java -cp target/test-classes:target/classes com.gildedrose.GenerateCases
 */
public final class GenerateCases {

    private static final Path JAVA_CSV =
            Paths.get("src", "test", "resources", "cases.csv");
    private static final Path DART_CSV =
            Paths.get("..", "dart", "test", "cases.csv");

    private static final String[] NAMES = {
            "+5 Dexterity Vest",
            "Aged Brie",
            "Elixir of the Mongoose",
            "Sulfuras, Hand of Ragnaros",
            "Backstage passes to a TAFKAL80ETC concert"
    };

    private static final int[] SELL_INS = { -5, -1, 0, 1, 2, 5, 6, 10, 11, 15 };
    private static final int[] QUALITIES = { 0, 1, 20, 48, 49, 50, 80 };

    static void main(String[] args) throws IOException {
        List<String> rows = new ArrayList<>();
        rows.add("name;sellIn;quality;expectedSellIn;expectedQuality");
        for (String name : NAMES) {
            for (int sellIn : SELL_INS) {
                for (int quality : QUALITIES) {
                    Item item = new Item(name, sellIn, quality);
                    new GildedRose(List.of(item)).updateQuality();
                    rows.add(name + ";" + sellIn + ";" + quality + ";"
                            + item.sellIn + ";" + item.quality);
                }
            }
        }

        Files.createDirectories(JAVA_CSV.getParent());
        Files.write(JAVA_CSV, rows);
        Files.createDirectories(DART_CSV.getParent());
        Files.write(DART_CSV, rows);

        System.out.println("Wrote " + (rows.size() - 1) + " cases to:");
        System.out.println("  " + JAVA_CSV.toAbsolutePath());
        System.out.println("  " + DART_CSV.toAbsolutePath());
    }
}
