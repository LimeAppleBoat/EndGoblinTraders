package com.jab125.egt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Random;

public class TradeWithoutEmeraldSellMapFactory implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final int price;
    private final StructureFeature<?> structure;
    private final MapIcon.Type iconType;
    private final int maxUses;
    private final int experience;

    public TradeWithoutEmeraldSellMapFactory(Item item, int price, StructureFeature<?> feature, MapIcon.Type iconType, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = price;
        this.structure = feature;
        this.iconType = iconType;
        this.maxUses = maxUses;
        this.experience = experience;
    }

    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        this.firstBuy.setCount(price);
        if (!(entity.world instanceof ServerWorld)) {
            return null;
        } else {
            ServerWorld serverWorld = (ServerWorld)entity.world;
            BlockPos blockPos = serverWorld.locateStructure(this.structure, entity.getBlockPos(), 100, true);
            if (blockPos != null) {
                ItemStack itemStack = FilledMapItem.createMap(serverWorld, blockPos.getX(), blockPos.getZ(), (byte)2, true, true);
                FilledMapItem.fillExplorationMap(serverWorld, itemStack);
                MapState.addDecorationsNbt(itemStack, blockPos, "+", this.iconType);
                String var10003 = this.structure.getName();
                itemStack.setCustomName(new TranslatableText("filled_map." + var10003.toLowerCase(Locale.ROOT)));
                return new TradeOffer(this.firstBuy, new ItemStack(Items.COMPASS), itemStack, this.maxUses, this.experience, 0.2F);
            } else {
                return null;
            }
        }
    }
}