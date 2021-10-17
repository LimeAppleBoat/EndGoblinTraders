package com.jab125.egt.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item END_GOBLIN_TRADER_SPAWN_EGG = new SpawnEggItem(ModEntities.END_GOBLIN_TRADER, 0x1a1a1a, 0xd1d1d1, new Item.Settings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "end_goblin_trader_spawn_egg"), END_GOBLIN_TRADER_SPAWN_EGG);
    }
}
