package com.jab125.egt.trades;

import net.hat.gt.trades.TradeWithoutEmerald;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class OnlyInEndTradeWithoutEmerald extends TradeWithoutEmerald {
    public OnlyInEndTradeWithoutEmerald(ItemConvertible item, int $, Item sellItem, int sellCount, int maxUses, int experience) {
        super(item, $, sellItem, sellCount, maxUses, experience);
    }

    public OnlyInEndTradeWithoutEmerald(ItemConvertible item, int $, Item sellItem, int sellCount, int experience) {
        super(item, $, sellItem, sellCount, experience);
    }

    public OnlyInEndTradeWithoutEmerald(ItemConvertible item, int $, ItemConvertible item2, int sC, Item sellItem, int sellCount, int maxUses, int experience) {
        super(item, $, item2, sC, sellItem, sellCount, maxUses, experience);
    }

    @Override
    public @Nullable TradeOffer create(Entity entity, Random random) {
        if (!(entity.world instanceof ServerWorld)) {
            return null;
        } else {
            ServerWorld serverWorld = (ServerWorld)entity.world;
            if (!serverWorld.getDimension().equals(DimensionType.THE_END)) {
                return null;
            }
            return super.create(entity, random);
        }

    }
}
