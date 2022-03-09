package com.jab125.egt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.TagKey;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Random;

public class TradeWithoutEmeraldSellMapFactory implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final int price;
    private final ItemStack secondBuy;
    private final int price2;
    private final TagKey<ConfiguredStructureFeature<?, ?>> structure;
    private final MapIcon.Type iconType;
    private final int maxUses;
    private final int experience;
    private final String nameKey;
    public TradeWithoutEmeraldSellMapFactory(Item item, int price, TagKey<ConfiguredStructureFeature<?, ?>> feature, String nameKey, MapIcon.Type iconType, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = price;
        this.secondBuy = new ItemStack(Items.COMPASS);
        this.price2 = 0;
        this.structure = feature;
        this.iconType = iconType;
        this.maxUses = maxUses;
        this.experience = experience;
        this.nameKey = nameKey;
    }

    public TradeWithoutEmeraldSellMapFactory(Item item, int price, Item item2, int price2, TagKey<ConfiguredStructureFeature<?, ?>> feature, String nameKey, MapIcon.Type iconType, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = price;
        this.secondBuy = new ItemStack(item2);
        this.price2 = price2;
        this.structure = feature;
        this.iconType = iconType;
        this.maxUses = maxUses;
        this.experience = experience;
        this.nameKey = nameKey;
    }

    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        this.firstBuy.setCount(price);
        this.secondBuy.setCount(price2);
        if (!(entity.world instanceof ServerWorld serverWorld)) {
            return null;
        } else {
            BlockPos blockPos = serverWorld.locateStructure(this.structure, entity.getBlockPos(), 100, true);
            if (blockPos != null) {
                ItemStack itemStack = FilledMapItem.createMap(serverWorld, blockPos.getX(), blockPos.getZ(), (byte) 2, true, true);
                FilledMapItem.fillExplorationMap(serverWorld, itemStack);
                MapState.addDecorationsNbt(itemStack, blockPos, "+", this.iconType);
                itemStack.setCustomName(new TranslatableText(this.nameKey));
                return new TradeOffer(this.firstBuy, secondBuy, itemStack, this.maxUses, this.experience, 0.2F);
            } else {
                return null;
            }
        }
    }
}