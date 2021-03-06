package com.jab125.egt.item;

import com.jab125.thonkutil.api.item.PotionableSword;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.collection.DefaultedList;

public class OpalSwordItem extends PotionableSword {
    public OpalSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    // due to a bug in thonkutil, i have to explicitly add this
    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {
            stacks.add(new ItemStack(this));
        }
    }

    @Override
    public boolean addPotionsToCreativeInventory() {
        return false;
    }
}
