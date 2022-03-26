package com.jab125.egt.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class OpalSwordTipItem extends Item {
        public OpalSwordTipItem(Settings settings) {
                super(settings);
        }

        @Override
        public Optional<TooltipData> getTooltipData(ItemStack stack) {
                return Optional.of(new OpalSwordTipTooltipData());
        }

        @Override
        public String getTranslationKey() {
                return Items.PAPER.getTranslationKey();
        }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                //tooltip.add(new TranslatableText("endgoblintraders.opal_sword_tip"));
                super.appendTooltip(stack, world, tooltip, context);
        }
}
