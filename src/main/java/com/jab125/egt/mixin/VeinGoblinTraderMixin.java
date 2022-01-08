package com.jab125.egt.mixin;

import com.jab125.egt.init.ModItems;
import net.hat.gt.entities.VeinGoblinTraderEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Collection;

@Mixin(VeinGoblinTraderEntity.class)
public class VeinGoblinTraderMixin {
    @ModifyVariable(method = "getPreferredFoods", at = @At(value = "RETURN"), index = 2, ordinal = 0, remap = false)
    private Collection<ItemStack> addEnchantedGoldenCarrot(Collection<ItemStack> collection) {
        collection.add(new ItemStack(ModItems.ENCHANTED_GOLDEN_CARROT));
        return collection;
    }
}
