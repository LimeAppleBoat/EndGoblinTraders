package com.jab125.egt.mixin;

import com.jab125.egt.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getAttackDamage", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void getAttackDamageMixin(ItemStack stack, EntityGroup group, CallbackInfoReturnable<Float> cir) {
        if (stack.isOf(ModItems.DURABILITY_TOTEM)) {
            cir.setReturnValue(0.0F);
        }
    }
}
