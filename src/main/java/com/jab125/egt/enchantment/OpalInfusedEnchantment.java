package com.jab125.egt.enchantment;

import com.jab125.egt.init.ModItems;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.MathHelper;

import java.util.function.Consumer;

public class OpalInfusedEnchantment extends DamageEnchantment {
    public OpalInfusedEnchantment(Rarity weight, int typeIndex, EquipmentSlot... slots) {
        super(weight, typeIndex, slots);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        //return 1.0F + (float)Math.max(0, level - 1) * 0.5F;
        return (float) Math.max(0, level) * 20F;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (!user.world.isClient() && !user.getMainHandStack().isOf(ModItems.DURABILITY_TOTEM)) {
            if ((int) (Math.random() * 13) == -1) {
                LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, target.world);
                lightningEntity.setPosition(target.getPos());
                target.world.spawnEntity(lightningEntity);
            }
            user.getMainHandStack().damage(user.getMainHandStack().getMaxDamage() / 10 - 1, user, (e) -> {
                e.sendToolBreakStatus(Hand.MAIN_HAND);
            });
        }
        super.onTargetDamaged(user, target, level);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    @Override
    public Text getName(int level) {
        return super.getName(level).copy().formatted(Formatting.LIGHT_PURPLE);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return false;
    }
}
