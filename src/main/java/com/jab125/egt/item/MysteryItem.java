package com.jab125.egt.item;

import com.jab125.egt.util.Util;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MysteryItem extends Item {
    /**
     * Will be useful in the future,
     * only be obtainable till 1.6.0
     */
    public MysteryItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal(this.getTranslationKey() + ".desc"));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (Util.field_2983_f()) {
            PlayerEntity playerEntity = (PlayerEntity) entity;
            int count = stack.getCount();
            stack.setCount(0);
            playerEntity.getInventory().insertStack(slot, new ItemStack(Items.EMERALD, count * 5));
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
