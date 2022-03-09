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
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class TradeWithoutEmeraldSellMapTo00Factory implements TradeOffers.Factory {
    private final ItemStack firstBuy;
    private final int price;
    private final MapIcon.Type iconType;
    private final int maxUses;
    private final int experience;

    public TradeWithoutEmeraldSellMapTo00Factory(Item item, int price, MapIcon.Type iconType, int maxUses, int experience) {
        this.firstBuy = new ItemStack(item);
        this.price = price;
        this.iconType = iconType;
        this.maxUses = maxUses;
        this.experience = experience;
    }

    @Nullable
    public TradeOffer create(Entity entity, Random random) {
        this.firstBuy.setCount(price);
        if (!(entity.world instanceof ServerWorld serverWorld)) {
            return null;
        } else {
            BlockPos blockPos = new BlockPos(0, 90, 0);
            if (serverWorld.getDimension().equals(DimensionType.THE_END)) {
                ItemStack itemStack = FilledMapItem.createMap(serverWorld, blockPos.getX(), blockPos.getZ(), (byte) 2, true, true);
                FilledMapItem.fillExplorationMap(serverWorld, itemStack);
                MapState.addDecorationsNbt(itemStack, blockPos, "+", this.iconType);
                itemStack.setCustomName(new TranslatableText("filled_map." + "end_fountain"));
                return new TradeOffer(this.firstBuy, new ItemStack(Items.COMPASS), itemStack, this.maxUses, this.experience, 0.2F);
            } else {
                return null;
            }
        }
    }
}