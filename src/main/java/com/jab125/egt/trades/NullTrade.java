package com.jab125.egt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

public class NullTrade implements TradeOffers.Factory {
    public NullTrade() {

    }

    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        return null;
    }
}
