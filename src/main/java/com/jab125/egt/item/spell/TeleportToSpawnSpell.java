package com.jab125.egt.item.spell;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class TeleportToSpawnSpell extends Spell {
    public TeleportToSpawnSpell(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        BlockPos pos = entity.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (world instanceof ServerWorld && !entity.hasVehicle() && !entity.hasPassengers() && entity.canUsePortals()) {
            RegistryKey<World> registryKey = World.OVERWORLD;
            ServerWorld serverWorld = ((ServerWorld) world).getServer().getWorld(registryKey);
            if (serverWorld == null || !entity.getEntityWorld().getRegistryKey().equals(World.END)) {
                return;
            }

            stack.setCount(0);
            entity.moveToWorld(serverWorld);
        }
    }
}
