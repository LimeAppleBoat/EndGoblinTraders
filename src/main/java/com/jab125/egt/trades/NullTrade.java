package com.jab125.egt.trades;

import net.minecraft.entity.Entity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class NullTrade implements TradeOffers.Factory {
    public NullTrade() {

    }

    @Nullable
    @Override
    public TradeOffer create(Entity entity, Random random) {
        return null;
    }
}
