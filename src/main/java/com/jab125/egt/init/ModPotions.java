package com.jab125.egt.init;

import com.jab125.egt.EGobT;
import com.jab125.thonkutil.api.RemovePotionRecipe;
import com.jab125.thonkutil.api.SkipPotion;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.util.registry.Registry;

public class ModPotions {
    public static final Potion EXTREMELY_POWERFUL_POISON;

    private static Potion register(String name, Potion potion) {
        RemovePotionRecipe.removeTippedArrow(potion);
        RemovePotionRecipe.removeSplashPotion(potion);
        RemovePotionRecipe.removeLingeringPotion(potion);
        SkipPotion.skipPotion(potion, 1);
        SkipPotion.skipPotion(potion, 2);
        SkipPotion.skipPotion(potion, 3);
        SkipPotion.skipPotion(potion, 4);
        SkipPotion.skipPotion(potion, 5);
        SkipPotion.skipPotion(potion, 6);
        SkipPotion.skipPotion(potion, 7);
        SkipPotion.skipPotion(potion, 8);
        SkipPotion.skipPotion(potion, 9);
        SkipPotion.skipPotion(potion, 10);
        return (Potion) Registry.register(Registry.POTION, EGobT.id(name), potion);
    }

    static {
        EXTREMELY_POWERFUL_POISON = register("extremely_powerful_poison", new Potion("poison", new StatusEffectInstance(StatusEffects.POISON, 200, 4)));
    }

    public static void registerPotions() {}
}
