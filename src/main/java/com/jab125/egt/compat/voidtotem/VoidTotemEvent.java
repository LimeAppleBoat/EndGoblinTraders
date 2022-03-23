package com.jab125.egt.compat.voidtotem;

import com.affehund.voidtotem.VoidTotem;
import com.affehund.voidtotem.api.ModEvents;
import com.affehund.voidtotem.core.LivingEntityAccessor;
import com.affehund.voidtotem.core.ModUtils;
import com.jab125.egt.init.ModItems;
import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.server.entity.TotemUseEvent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;

import static com.affehund.voidtotem.core.ModUtils.*;

public class VoidTotemEvent {
    @SubscribeEvent(priority = SubscribeEvent.Priority.HIGH)
    public static void useVoidTotem(TotemUseEvent event) {
        if(isDimensionBlacklisted(event.getEntity()) || !isOutOfWorld(event.getSource(), event.getEntity())) return;
        var stack = event.findTotem(ModItems.DURABILITY_VOID_TOTEM);
        if (stack.isEmpty()) {
            if (VoidTotem.CONFIG.USE_TOTEM_FROM_INVENTORY && event.getEntity() instanceof ServerPlayerEntity serverPlayerEntity)
                stack = scanInventory(serverPlayerEntity, ModItems.DURABILITY_VOID_TOTEM);
            if (stack.isEmpty())return;
        }

        event.incrementStat(stack.getItem());
        if (event.getEntity().hasPlayerRider()) {
            event.getEntity().removeAllPassengers();
        }

        event.getEntity().stopRiding();
        ((LivingEntityAccessor) event.getEntity()).setFallDamageImmune(true);
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


    private static ItemStack scanInventory(ServerPlayerEntity player, Item item) {
        var q = player.getInventory().main;
        for (ItemStack itemStack : q) {
            if (itemStack.isOf(item)) return itemStack;
        }
        return ItemStack.EMPTY;
    }

    private static ActionResult voidTotemCancel(ItemStack itemStack, LivingEntity livingEntity, DamageSource source) {
        if (itemStack.isOf(ModItems.DURABILITY_VOID_TOTEM)) return ActionResult.CONSUME; // already have a custom method for that
        if (itemStack.isOf(ModItems.DURABILITY_TOTEM)) if (useTotem(itemStack, livingEntity, source)) return ActionResult.CONSUME_PARTIAL;
        return ActionResult.PASS;
    }

    public static void voidTotemEvent() {
        ModEvents.VOID_TOTEM_EVENT.register(VoidTotemEvent::voidTotemCancel);
    }

    private static boolean useTotem(ItemStack itemStack, LivingEntity livingEntity, DamageSource source) {
        if(isDimensionBlacklisted(livingEntity) || !isOutOfWorld(source, livingEntity)) return false;
        var stack = itemStack;
        if (stack.isEmpty()) {
            if (VoidTotem.CONFIG.USE_TOTEM_FROM_INVENTORY && livingEntity instanceof ServerPlayerEntity serverPlayerEntity)
                stack = scanInventory(serverPlayerEntity, ModItems.DURABILITY_TOTEM);
            if (stack.isEmpty())return false;
        }

        if (!stack.equals(ItemStack.EMPTY)) {
            if (livingEntity instanceof ServerPlayerEntity player) {
                player.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
            }

        }
        if (livingEntity.hasPlayerRider()) {
            livingEntity.removeAllPassengers();
        }

        livingEntity.stopRiding();
        ((LivingEntityAccessor) livingEntity).setFallDamageImmune(true);
        teleportToSavePosition(livingEntity);
        ModUtils.playActivateAnimation(stack, livingEntity);
        stack.damage(livingEntity.world.getLevelProperties().isHardcore() ? 5 : 10, livingEntity, (e) ->{
            for (var slot : EquipmentSlot.values()) {
                e.sendEquipmentBreakStatus(slot);
            }
        });
        if (stack.getDamage() >= stack.getMaxDamage()) stack.decrement(1);
        return true;
    }
}
