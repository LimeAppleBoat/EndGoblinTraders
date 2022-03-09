package com.jab125.egt.item;

import com.jab125.egt.init.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.jab125.egt.init.ModEnchantments.OPAL_INFUSED;
import static com.jab125.thonkutil.util.Util.isModInstalled;

public class DurabilityTotem extends Item {
    public DurabilityTotem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        //return super.isEnchantable(stack);
        return false;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        if (stack.getItem() instanceof DurabilityTotem) {
            return super.getRarity(stack);
        } else {
            return switch (this.rarity) {
                case COMMON, UNCOMMON -> Rarity.RARE;
                case RARE -> Rarity.EPIC;
                case EPIC -> this.rarity;
            };
        }
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText(OPAL_INFUSED.getTranslationKey()).formatted(Formatting.LIGHT_PURPLE));

        if (stack.getItem().equals(ModItems.DURABILITY_VOID_TOTEM) && !isModInstalled("voidtotem")) {
            tooltip.add(new TranslatableText(ModItems.DURABILITY_VOID_TOTEM.getTranslationKey() + ".disabled").formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
