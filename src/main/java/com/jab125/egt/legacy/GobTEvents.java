package com.jab125.egt.legacy;

import com.mrcrayfish.goblintraders.entity.AbstractGoblinEntity;
import com.mrcrayfish.goblintraders.trades.TradeRarity;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public interface GobTEvents {
    Event<OnAttemptSpawn> ON_ATTEMPT_SPAWN = EventFactory.createArrayBacked(OnAttemptSpawn.class,
            (listeners) -> (goblinType, serverWorld, pos) -> {
                for(OnAttemptSpawn listener : listeners) {
                    ActionResult result = listener.interact(goblinType, serverWorld, pos);
                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    interface OnAttemptSpawn {
        ActionResult interact(EntityType<?> goblinTraderType, ServerWorld world, BlockPos safestPos);
    }
}