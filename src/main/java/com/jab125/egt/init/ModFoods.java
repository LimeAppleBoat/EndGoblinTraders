package com.jab125.egt.init;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoods {
    public static final FoodComponent ENCHANTED_GOLDEN_CARROT = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 3000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 3), 1.0F).alwaysEdible().build();
}
