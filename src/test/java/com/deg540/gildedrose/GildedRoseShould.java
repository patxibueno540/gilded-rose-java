package com.deg540.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseShould {

    @Test
    public void decrease_the_quality_by_one_when_not_expired() {
        Item fooItem = new Item("foo", 10, 20);
        GildedRose gildedRose = new GildedRose(fooItem);
        
        gildedRose.updateQuality();
        
        assertEquals(19, fooItem.getQuality());
    }

    @Test 
    public void decrease_the_quality_by_two_when_expired() {
        Item fooItem = new Item("foo", 0, 20);
        GildedRose gildedRose = new GildedRose(fooItem);
        
        gildedRose.updateQuality();
        
        assertEquals(18, fooItem.getQuality());
    }

    @Test 
    public void increase_the_quality_of_aged_brie_by_one_when_not_expired() {
        Item agedBrie = new Item("Aged Brie", 10, 20);
        GildedRose gildedRose = new GildedRose(agedBrie);
        
        gildedRose.updateQuality();
        
        assertEquals(21, agedBrie.getQuality());
    }
    
    @Test 
    public void increase_the_quality_of_aged_brie_by_two_when_expired() {
        Item agedBrie = new Item("Aged Brie", 0, 20);
        GildedRose gildedRose = new GildedRose(agedBrie);
        
        gildedRose.updateQuality();
        
        assertEquals(22, agedBrie.getQuality());
    }

    @Test 
    public void increase_the_quality_of_backstage_passes_by_one_when_more_than_ten_days_left() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        GildedRose gildedRose = new GildedRose(backstagePasses);
        
        gildedRose.updateQuality();
        
        assertEquals(21, backstagePasses.getQuality());
    }

    @Test 
    public void increase_the_quality_of_backstage_passes_by_two_when_ten_days_or_less_left() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        GildedRose gildedRose = new GildedRose(backstagePasses);
        
        gildedRose.updateQuality();
        
        assertEquals(22, backstagePasses.getQuality());
    }

    @Test 
    public void increase_the_quality_of_backstage_passes_by_three_when_five_days_or_less_left() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        GildedRose gildedRose = new GildedRose(backstagePasses);
        
        gildedRose.updateQuality();
        
        assertEquals(23, backstagePasses.getQuality());
    }

    @Test 
    public void drop_the_quality_of_backstage_passes_to_zero_when_expired() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        GildedRose gildedRose = new GildedRose(backstagePasses);
        
        gildedRose.updateQuality();
        
        assertEquals(0, backstagePasses.getQuality());
    }

    @Test 
    public void never_decrease_the_quality_of_sulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        GildedRose gildedRose = new GildedRose(sulfuras);
        
        gildedRose.updateQuality();
        
        assertEquals(80, sulfuras.getQuality());
    }

    @Test 
    public void never_decrease_the_quality_below_zero() {
        Item fooItem = new Item("foo", 10, 0);
        GildedRose gildedRose = new GildedRose(fooItem);
        
        gildedRose.updateQuality();
        
        assertEquals(0, fooItem.getQuality());
    }

    @Test 
    public void never_increase_the_quality_above_fifty() {
        Item agedBrie = new Item("Aged Brie", 10, 50);
        GildedRose gildedRose = new GildedRose(agedBrie);
        
        gildedRose.updateQuality();
        
        assertEquals(50, agedBrie.getQuality());
    }

    @Test 
    public void conjured_items_decrease_two_in_quality_when_not_expired() {
        Item conjuredItem = new Item("Conjured Mana Cake", 10, 20);
        GildedRose gildedRose = new GildedRose(conjuredItem);
        
        gildedRose.updateQuality();
        
        assertEquals(18, conjuredItem.getQuality());
    }

    @Test
    public void conjured_items_decrease_four_in_quality_when_expired() {
        Item conjuredItem = new Item("Conjured Mana Cake", 0, 20);
        GildedRose gildedRose = new GildedRose(conjuredItem);
        
        gildedRose.updateQuality();
        
        assertEquals(16, conjuredItem.getQuality());
    }

    @Test 
    public void decrease_sellIn_for_not_legendary_items() {
        Item fooItem = new Item("foo", 10, 20);
        GildedRose gildedRose = new GildedRose(fooItem);
        
        gildedRose.updateQuality();
        
        assertEquals(9, fooItem.getSellIn());
    }

    @Test 
    public void not_decrease_sellIn_for_legendary_items() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        GildedRose gildedRose = new GildedRose(sulfuras);
        
        gildedRose.updateQuality();
        
        assertEquals(0, sulfuras.getSellIn());
    }

    @Test 
    public void never_increase_quality_for_legendary_items() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        GildedRose gildedRose = new GildedRose(sulfuras);
        
        gildedRose.updateQuality();
        
        assertEquals(80, sulfuras.getQuality());
    }

    @Test 
    public void never_increase_aged_brie_quality_above_fifty() {
        Item agedBrie = new Item("Aged Brie", -2, 49);
        GildedRose gildedRose = new GildedRose(agedBrie);
        
        gildedRose.updateQuality();
        
        assertEquals(50, agedBrie.getQuality());
    }

    @Test 
    public void never_increase_backstage_pass_quality_above_fifty() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        GildedRose gildedRose = new GildedRose(backstagePasses);
        
        gildedRose.updateQuality();
        
        assertEquals(50, backstagePasses.getQuality());
    }

    @Test 
    public void never_decrease_item_quality_below_zero() {
        Item fooItem = new Item("foo", 10, 0);
        GildedRose gildedRose = new GildedRose(fooItem);
        
        gildedRose.updateQuality();
        
        assertEquals(0, fooItem.getQuality());
    }
}