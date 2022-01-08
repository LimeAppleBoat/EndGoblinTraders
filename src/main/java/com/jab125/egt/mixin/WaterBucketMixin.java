package com.jab125.egt.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BucketItem.class)
public class WaterBucketMixin {
    private static ItemStack returnItem;

    @Inject(method = "getEmptiedStack", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private static void append(ItemStack stack, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        returnItem = stack;
        if (returnItem.isOf(Items.WATER_BUCKET)) {
            if (EnchantmentHelper.get(returnItem).containsKey(Enchantments.INFINITY)) {
                cir.setReturnValue(returnItem);
            }
        }
    }
}
