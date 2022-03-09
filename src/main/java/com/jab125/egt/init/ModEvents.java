package com.jab125.egt.init;

import com.jab125.thonkutil.api.annotations.SubscribeEvent;
import com.jab125.thonkutil.api.events.server.entity.TotemUseEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Hand;

import static com.jab125.egt.util.Util.damageItem;

public class ModEvents {
    @SubscribeEvent
    public static void useDurabilityTotem(TotemUseEvent event) {
        if (event.getSource().isOutOfWorld()) return;
        var totem  = event.findTotem(ModItems.DURABILITY_TOTEM);
        if (totem.isEmpty()) return;
        event.saveEntity();
        event.setTotemActivateItem(totem.copy());
        event.incrementStat(totem.getItem());
        event.regenerateHealth();
        event.playActivateAnimation();
        event.getEntity().clearStatusEffects();
        event.getEntity().addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        event.getEntity().addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        event.getEntity().addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        totem.damage(event.getEntity().world.getLevelProperties().isHardcore() ? 5 : 10, event.getEntity(), (e) ->{
            for (var slot : EquipmentSlot.values()) {
                e.sendEquipmentBreakStatus(slot);
            }
        });
        if (totem.getDamage() >= totem.getMaxDamage()) totem.decrement(1);
    }
}
