package com.jab125.egt.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class Util {
    public static boolean field_2983_f() {
        @SuppressWarnings("OptionalGetWithoutIsPresent") String versionString = FabricLoader.getInstance().getModContainer("endgoblintraders").get().getMetadata().getVersion().getFriendlyString();
        Version version = new Version(versionString);
        return version.compareTo(new Version("1.1.0")) >= 0;
    }

    public static boolean damageItem(ItemStack itemStack, int amount, Random random, @Nullable LivingEntity entity) {
        if (!itemStack.isDamageable()) {
            return false;
        } else {
            int i;
            if (amount > 0) {
                i = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, itemStack);
                int j = 0;

                for(int k = 0; i > 0 && k < amount; ++k) {
                    if (UnbreakingEnchantment.shouldPreventDamage(itemStack, i, random)) {
                        ++j;
                    }
                }

                amount -= j;
                if (amount <= 0) {
                    return false;
                }
            }

            if (entity != null && amount != 0 && entity instanceof ServerPlayerEntity playerEntity) {
                Criteria.ITEM_DURABILITY_CHANGED.trigger(playerEntity, itemStack, itemStack.getDamage() + amount);
            }

            i = itemStack.getDamage() + amount;
            itemStack.setDamage(i);
            return i >= itemStack.getMaxDamage();
        }
    }
}
