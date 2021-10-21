package com.jab125.egt.mixin;

import com.jab125.egt.EGobT;
import com.jab125.egt.init.ModItems;
import net.hat.gt.GobT;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow
    public abstract String getTranslationKey();

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.isOf(ModItems.OPAL) && !EGobT.config.GENERATE_OPAL_ORE) {
            tooltip.add(new TranslatableText(this.getTranslationKey() + ".desc").formatted(Formatting.GRAY));
        }
    }
}
