package com.jab125.egt.init;

import com.jab125.egt.enchantment.OpalInfusedEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {
    /**
     * Opal Infused is an only trade item, it can't be combined :)
     */
    public static final Enchantment OPAL_INFUSED = new OpalInfusedEnchantment(Enchantment.Rarity.VERY_RARE, 0, EquipmentSlot.MAINHAND);

    public static void registerEnchantments() {
        Registry.register(Registry.ENCHANTMENT, new Identifier("endgoblintraders", "opal_infused"), OPAL_INFUSED);
    }
}
