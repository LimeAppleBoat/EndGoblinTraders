package com.jab125.egt.compat.voidtotem;

import com.affehund.voidtotem.VoidTotem;
import com.affehund.voidtotem.core.IPlayerEntityMixinAccessor;
import com.affehund.voidtotem.core.ModUtils;
import com.jab125.egt.init.ModItems;
import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.server.entity.TotemUseEvent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import static com.affehund.voidtotem.core.ModUtils.*;

public class VoidTotemEvent {
    @SubscribeEvent(priority = SubscribeEvent.Priority.HIGH)
    public static void useVoidTotem(TotemUseEvent event) {
        if(isDimensionBlacklisted(event.getEntity()) || !isOutOfWorld(event.getSource(), event.getEntity())) return;
        var stack = event.findTotem(ModItems.DURABILITY_VOID_TOTEM);
        if (stack.isEmpty()) return;
        event.incrementStat(stack.getItem());
        if (event.getEntity().hasPlayerRider()) {
            event.getEntity().removeAllPassengers();
        }

        event.getEntity().stopRiding();
        event.getEntity().addScoreboardTag("voidtotem_living_falling");
        event.saveEntity();
        teleportToSavePosition(event.getEntity());
        event.setTotemActivateItem(stack);
        event.playActivateAnimation();
        stack.damage(event.getEntity().world.getLevelProperties().isHardcore() ? 5 : 10, event.getEntity(), (e) ->{
            for (var slot : EquipmentSlot.values()) {
                e.sendEquipmentBreakStatus(slot);
            }
        });
        if (stack.getDamage() >= stack.getMaxDamage()) stack.decrement(1);
    }

    // supports non player characters
    public static void teleportToSavePosition(LivingEntity entity) {
        long lastBlockPos = entity.getBlockPos().asLong();
        BlockPos teleportPos = BlockPos.fromLong(lastBlockPos);
        BlockPos positionInRadius = positionInRadius(entity, teleportPos);
        if (positionInRadius == null) {
            entity.teleport((double)teleportPos.getX(), (double)(entity.world.getTopY() + VoidTotem.CONFIG.TELEPORT_HEIGHT_OFFSET), (double)teleportPos.getZ());
            if (entity instanceof ServerPlayerEntity player)
            player.networkHandler.floatingTicks = 0;
        }
    }

    // supports non player characters
    public static BlockPos positionInRadius(LivingEntity entity, BlockPos teleportPos) {
        for(int i = 0; i < 16; ++i) {
            int maxBuildHeight = entity.world.getTopY();
            double x = (double)teleportPos.getX() + (entity.getRandom().nextDouble() - 0.5D) * 4.0D;
            double y = MathHelper.clamp((double)(entity.getRandom().nextInt() * maxBuildHeight), 0.0D, (double)(maxBuildHeight - 1));
            double z = (double)teleportPos.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 4.0D;
            BlockPos pos = new BlockPos(x, y, z);
            if (entity.teleport(x, y, z, true)) {
                return pos;
            }
        }

        return null;
    }
}
