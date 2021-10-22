package com.jab125.egt.init;

import com.jab125.egt.item.GlintItem;
import com.jab125.egt.item.MysteryItem;
import com.jab125.egt.item.spell.TeleportToSpawnSpell;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item END_GOBLIN_TRADER_SPAWN_EGG = new SpawnEggItem(ModEntities.END_GOBLIN_TRADER, 0x1a1a1a, 0xd1d1d1, new Item.Settings().group(ItemGroup.MISC));
    public static final Item MYSTERY_ITEM = new MysteryItem(new FabricItemSettings().maxCount(1).rarity(Rarity.COMMON).fireproof().maxCount(1));
    public static final Item TELEPORT_TO_SPAWN_SPELL = new TeleportToSpawnSpell(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof().maxCount(1));
    public static final Item ENCHANTED_GOLDEN_CARROT = new GlintItem((new Item.Settings()).group(null).food(ModFoods.ENCHANTED_GOLDEN_CARROT));
    public static final Item OPAL = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "end_goblin_trader_spawn_egg"), END_GOBLIN_TRADER_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "mystery_item"), MYSTERY_ITEM);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "teleport_to_spawn_spell"), TELEPORT_TO_SPAWN_SPELL);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "enchanted_golden_carrot"), ENCHANTED_GOLDEN_CARROT);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "opal"), OPAL);
    }
}
