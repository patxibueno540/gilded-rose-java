package com.deg540.gildedrose;

import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    private static final int BACKSTAGE_PASS_STANDAR_QUALITY_FACTOR = 1;
    private static final int CONCERT_CLOSENESS_SECOND_THIRD_MARK = 11;
    private static final int CONCERT_CLOSENESS_LAST_THIRD_MARK = 6;
    private static final int BACKSTAGE_QUALITY_FACTOR_LESS_THAN_ELEVEN_DAYS = 2;
    private static final int BACKSTAGE_PASS_QUALITY_FACTOR_LESS_THAN_SIX_DAYS = 3;
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;
    private static final int CONJURED_FACTOR = 2;
    private static final int EXPIRED_FACTOR = 2;
    private static final int QUALITY_BASE_FACTOR = 1;
    private static List<Item> items = null;

    public GildedRose(Item... items) {
        if (GildedRose.items == null) {
            GildedRose.items = new ArrayList<>();
        } else {
            GildedRose.items.clear();
        }
        
        if (items.length == 0) {
            initializeItemArray();
        } else {
            for (Item item : items) {
                GildedRose.items.add(item);
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        GildedRose gildedRose = new GildedRose();

        gildedRose.updateQuality();
    }

    private void initializeItemArray() {
        items = new ArrayList<>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
    }


    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }        
    }
    private static void updateItem(Item currentItem) {
        if (isSulfurus(currentItem)) {
            return;
        }
        decreaseSellIn(currentItem);
        int expiredFactor = hasExpired(currentItem) ? EXPIRED_FACTOR : 1;

        if (isAgedBrie(currentItem)) {
            updateAgedBrieQuality(currentItem, expiredFactor);
            return;
        }
        if (isBackstagePass(currentItem)) {
            updateBackstagePassQuality(currentItem, expiredFactor);
            return;
        }
        if (isConjured(currentItem)) {
            updateConjuredItemQuality(currentItem, expiredFactor);
            return;
        }
        updateBaseItemQuality(currentItem, expiredFactor);
}

    private static boolean hasExpired(Item currentItem) {
        return currentItem.getSellIn() < 0;
    }

    private static void updateBaseItemQuality(Item currentItem, int expiredFactor) {
        decreaseQuality(currentItem, QUALITY_BASE_FACTOR * expiredFactor);
    }

    private static void updateConjuredItemQuality(Item currentItem, int expiredFactor) {
        decreaseQuality(currentItem, QUALITY_BASE_FACTOR * expiredFactor * CONJURED_FACTOR);
    }

    private static void updateAgedBrieQuality(Item currentItem, int expiredFactor) {
        increaseQuality(currentItem, QUALITY_BASE_FACTOR * expiredFactor);
    }

    private static void updateBackstagePassQuality(Item currentItem, int expiredFactor) {
        if (hasExpired(currentItem)) {
            currentItem.setQuality(0);
        } else {
            int concertClossenessFactor = BACKSTAGE_PASS_STANDAR_QUALITY_FACTOR;
            if (currentItem.getSellIn() < CONCERT_CLOSENESS_LAST_THIRD_MARK) {
                concertClossenessFactor = BACKSTAGE_PASS_QUALITY_FACTOR_LESS_THAN_SIX_DAYS;
            } else if (currentItem.getSellIn() < CONCERT_CLOSENESS_SECOND_THIRD_MARK) {
                concertClossenessFactor = BACKSTAGE_QUALITY_FACTOR_LESS_THAN_ELEVEN_DAYS;
            }
            increaseQuality(currentItem, QUALITY_BASE_FACTOR * expiredFactor * concertClossenessFactor);
        }
    }

    private static boolean isConjured(Item currentItem) {
        return currentItem.getName().toLowerCase().contains("conjured");
    }

    private static void decreaseSellIn(Item currentItem) {
        currentItem.setSellIn(currentItem.getSellIn() - 1);
    }

    private static void increaseQuality(Item currentItem, int amount) {
        currentItem.setQuality(Math.min(MAX_QUALITY, currentItem.getQuality() + amount));
    }

    private static void decreaseQuality(Item currentItem, int amount) {
        currentItem.setQuality(Math.max(MIN_QUALITY, currentItem.getQuality() - amount));
    }

    private static boolean isBackstagePass(Item i) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(i.getName());
    }

    private static boolean isAgedBrie(Item i) {
        return "Aged Brie".equals(i.getName());
    }

    private static boolean isSulfurus(Item i) {
        return "Sulfuras, Hand of Ragnaros".equals(i.getName());
    }
}