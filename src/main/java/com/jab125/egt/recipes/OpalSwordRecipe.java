package com.jab125.egt.recipes;

import com.google.common.collect.Lists;
import com.jab125.egt.EGobT;
import com.jab125.egt.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class OpalSwordRecipe extends SpecialCraftingRecipe {
    public OpalSwordRecipe(Identifier id) {
        super(id);
    }

    public boolean matches(CraftingInventory craftingInventory, World world) {
        List<ItemStack> list = Lists.newArrayList();

        // for every slot
        for (int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (!itemStack.isEmpty()) {
                list.add(itemStack);
                if (list.size() > 1) {
                    ItemStack itemStack2 = list.get(0);

                    for (var stack : list) {
                        if (!stack.isOf(ModItems.OPAL_SWORD) && !stack.isOf(Items.POTION)) return false;
                        //if (!stack.isOf(Items.POTION) && !stack.isOf(Items.POTION)) return false;
                        if (stack.isOf(ModItems.OPAL_SWORD) && PotionUtil.getPotionEffects(stack).size() != 0) return false;
                    }
                }
            }
        }

        return list.size() == 2;
    }

    public ItemStack craft(CraftingInventory craftingInventory) {
        ItemStack sword = ItemStack.EMPTY;
        ItemStack potion = ItemStack.EMPTY;
        for (var i = 0; i < craftingInventory.size(); ++i) {
            if (craftingInventory.getStack(i).isOf(ModItems.OPAL_SWORD)) sword = craftingInventory.getStack(i);
            if (craftingInventory.getStack(i).isOf(Items.POTION)) potion = craftingInventory.getStack(i);
        }
        if (sword.isEmpty() || potion.isEmpty()) return ItemStack.EMPTY;
        var stack = new ItemStack(ModItems.OPAL_SWORD);
        stack.setDamage(sword.getDamage());
        EnchantmentHelper.get(sword).forEach(stack::addEnchantment);
        PotionUtil.setPotion(stack, PotionUtil.getPotion(potion));
        return stack;
        //return ItemStack.EMPTY;
    }

    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return EGobT.OPAL_SWORD_RECIPE;
    }
}
