package com.jab125.egt.mixin;

import com.jab125.egt.init.ModEnchantments;
import com.jab125.egt.init.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Consumer;

@Mixin(LivingEntity.class)
public class EntityMixin {
    LivingEntity livingEntity;
    @Inject(method = "tryUseTotem", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void tryTotemInject(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        livingEntity = (LivingEntity) (Object) this;
        if (source.isOutOfWorld()) {
            cir.cancel();
        } else {
            ItemStack itemStack = null;
            Hand[] var4 = Hand.values();
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Hand hand = var4[var6];
                ItemStack itemStack2 = this.livingEntity.getStackInHand(hand);
                if (itemStack2.isOf(ModItems.DURABILITY_TOTEM)) {
                    if (EnchantmentHelper.get(itemStack2).containsKey(ModEnchantments.OPAL_INFUSED)) {
                        itemStack = itemStack2.copy();
                        if (!livingEntity.world.isClient()) {
                            itemStack2.damage(livingEntity.world.getLevelProperties().isHardcore() ? 5 : 10, this.livingEntity, (e) -> {
                                e.sendToolBreakStatus(hand);
                            });
                        }
                        break;
                    } else {
                        itemStack = itemStack2.copy();
                        itemStack2.decrement(1);
                        break;
                    }
                }
            }
            if (itemStack != null) {
                if (this.livingEntity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)this.livingEntity;
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
                    //Criteria.USED_TOTEM.trigger(serverPlayerEntity, itemStack);
                    Criteria.USED_TOTEM.trigger(serverPlayerEntity, new ItemStack(Items.TOTEM_OF_UNDYING));
                }

                this.livingEntity.setHealth(1.0F);
                this.livingEntity.clearStatusEffects();
                this.livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
                this.livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
                this.livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
                this.livingEntity.world.sendEntityStatus(this.livingEntity, (byte)35);
            }
            if (itemStack != null) {
                cir.setReturnValue(true);
            }
        }
    }
}
