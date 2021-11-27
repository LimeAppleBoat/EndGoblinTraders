package com.jab125.egt.mixin;

import net.minecraft.enchantment.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Enchantment.class)
public class InfinityEnchantMixin {
    @Inject(method = "isAcceptableItem", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void injectToEnchant(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isOf(Items.WATER_BUCKET) && ((Enchantment) (Object) this).equals(Enchantments.INFINITY)) {
            cir.setReturnValue(true);
        }
    }
}
