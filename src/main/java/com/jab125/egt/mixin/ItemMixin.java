package com.jab125.egt.mixin;

import com.jab125.egt.EGobT;
import com.jab125.egt.init.ModBlocks;
import com.jab125.egt.init.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract String getTranslationKey();


    @Inject(method = "appendTooltip", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void append(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (stack.isOf(ModBlocks.OPAL_ORE.asItem()) && !EGobT.config.GENERATE_OPAL_ORE) {
            tooltip.add(Text.translatable(this.getTranslationKey() + ".disabled").formatted(Formatting.GRAY));
        }
        if (stack.isOf(ModItems.ENCHANTED_GOLDEN_CARROT)) {
            tooltip.add(Text.literal("WIP").formatted(Formatting.GRAY));
        }
    }
}
