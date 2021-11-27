package com.jab125.egt.item;

import com.jab125.egt.init.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;

public class DurabilityTotem extends Item {
    public DurabilityTotem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        //return super.isEnchantable(stack);
        return false;
    }

    @Override
    public String getTranslationKey() {
        return Items.TOTEM_OF_UNDYING.getTranslationKey();
    }
    @Override
    public Rarity getRarity(ItemStack stack) {
        if (!stack.isOf(ModItems.DURABILITY_TOTEM)) {
            return super.getRarity(stack);
        } else {
            if (!stack.hasEnchantments()) {
                return this.rarity;
            } else {
                return switch (this.rarity) {
                    case COMMON, UNCOMMON, RARE, EPIC -> Rarity.EPIC;
                };
            }
        }
    }
}
