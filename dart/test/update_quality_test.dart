import 'dart:io';

import 'package:test/test.dart';
import 'package:gilded_rose/gilded_rose.dart';

/// One test per row in `test/cases.csv`.
///
/// Each row encodes an input item (`name`, `sellIn`, `quality`) and the
/// expected state after a single `GildedRose.updateQuality()` call. The CSV
/// is generated once from the original implementation (see the Java
/// `GenerateCases` tool) and treated as the frozen oracle during refactoring.
const _casesPath = 'test/cases.csv';

void main() {
  final lines = File(_casesPath).readAsLinesSync()
    ..removeWhere((l) => l.trim().isEmpty);
  // Skip header.
  final rows = lines.skip(1);

  for (final line in rows) {
    final parts = line.split(';');
    final name = parts[0];
    final sellIn = int.parse(parts[1]);
    final quality = int.parse(parts[2]);
    final expectedSellIn = int.parse(parts[3]);
    final expectedQuality = int.parse(parts[4]);

    test(
      '$name (sellIn=$sellIn, quality=$quality) -> '
      'sellIn=$expectedSellIn, quality=$expectedQuality',
      () {
        // given an item built from the CSV row
        final item = Item(name, sellIn, quality);

        // when one day passes
        GildedRose(<Item>[item]).updateQuality();

        // then the resulting state matches the captured oracle
        expect(item.sellIn, expectedSellIn, reason: 'sellIn');
        expect(item.quality, expectedQuality, reason: 'quality');
      },
    );
  }
}
