package com.jab125.egt.item.spell;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Spell extends Item {
    public Spell(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("--> CLICK HERE FOR FREE ROBUX <--").formatted(Formatting.OBFUSCATED).formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
