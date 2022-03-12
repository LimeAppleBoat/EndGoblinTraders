package com.jab125.egt.item;

import com.jab125.egt.init.ModItems;
import com.terraformersmc.modmenu.util.mod.Mod;
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
import static com.jab125.egt.util.VoidTotemQuarantine.showTooltip;
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
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    protected String getOrCreateTranslationKey() {
        if (this == ModItems.DURABILITY_TOTEM) {
            return Items.TOTEM_OF_UNDYING.getTranslationKey();
        } else if (this == ModItems.DURABILITY_VOID_TOTEM) {
            if (isModInstalled("voidtotem")) {
                return "item.voidtotem.totem_of_void_undying";
            }
        }
        return super.getOrCreateTranslationKey();
    }
}
