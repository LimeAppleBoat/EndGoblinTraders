//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jab125.egt.trades;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers.Factory;
import org.jetbrains.annotations.Nullable;

public class TradeWithoutEmeraldItemStack implements Factory {
    private final ItemStack firstBuy;
    private final ItemStack secondBuy;
    private final int secondPrice;
    private final int price;
    private final ItemStack sell;
    private final int sellCount;
    private final int maxUses;
    private final int experience;
    private final float multiplier;

    public TradeWithoutEmeraldItemStack(ItemStack item, int $, ItemStack sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = ItemStack.EMPTY;
        this.secondPrice = 0;
        this.sell = sellItem;
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    public TradeWithoutEmeraldItemStack(ItemStack item, int $, ItemStack sellItem, int sellCount, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = ItemStack.EMPTY;
        this.secondPrice = 0;
        this.sell = sellItem;
        this.sellCount = sellCount;
        this.maxUses = 1;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    public TradeWithoutEmeraldItemStack(ItemStack item, int $, ItemStack item2, int sC, ItemStack sellItem, int sellCount, int maxUses, int experience) {
        this.firstBuy = item;
        this.price = $;
        this.secondBuy = item2;
        this.secondPrice = sC;
        this.sell = sellItem;
        this.sellCount = sellCount;
        this.maxUses = maxUses;
        this.experience = experience;
        this.multiplier = 0.05F;
    }

    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        firstBuy.setCount(price);
        secondBuy.setCount(secondPrice);
        sell.setCount(sellCount);
        return new TradeOffer(firstBuy, secondBuy, this.sell, this.maxUses, this.experience, this.multiplier);
    }
}
