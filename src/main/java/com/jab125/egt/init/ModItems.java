package com.jab125.egt.init;

import com.jab125.egt.item.*;
import com.jab125.egt.item.spell.TeleportToSpawnSpell;
import com.jab125.thonkutil.api.CapeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.jab125.thonkutil.util.Util.isModInstalled;


public class ModItems {
    public static final Item END_GOBLIN_TRADER_SPAWN_EGG = new SpawnEggItem(ModEntities.END_GOBLIN_TRADER, 0x1a1a1a, 0xd1d1d1, new Item.Settings().group(ItemGroup.MISC));
    public static final Item MYSTERY_ITEM = new MysteryItem(new FabricItemSettings().maxCount(1).rarity(Rarity.COMMON).fireproof().maxCount(1));
    public static final Item TELEPORT_TO_SPAWN_SPELL = new TeleportToSpawnSpell(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC).fireproof().maxCount(1));
    public static final Item ENCHANTED_GOLDEN_CARROT = new GlintItem((new Item.Settings()).group(null).food(ModFoods.ENCHANTED_GOLDEN_CARROT));
    public static final Item OPAL = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item DURABILITY_TOTEM = new DurabilityTotem((new Item.Settings()).maxDamage(30).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON));
    public static final Item DURABILITY_VOID_TOTEM = new DurabilityTotem((new Item.Settings()).maxDamage(30).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON));
    public static final Item END_STONE_CAPE = new CapeItem((new Item.Settings()).rarity(Rarity.RARE));
    public static final Item OPAL_SWORD = new OpalSwordItem(ModToolMaterials.OPAL, 1, 151, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item OPAL_SWORD_TIP = new OpalSwordTipItem(new FabricItemSettings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "end_goblin_trader_spawn_egg"), END_GOBLIN_TRADER_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "mystery_item"), MYSTERY_ITEM);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "teleport_to_spawn_spell"), TELEPORT_TO_SPAWN_SPELL);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "enchanted_golden_carrot"), ENCHANTED_GOLDEN_CARROT);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "opal"), OPAL);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "durability_totem"), DURABILITY_TOTEM);
        //if (isModInstalled("voidtotem"))
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "durability_void_totem"), DURABILITY_VOID_TOTEM);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "end_stone_cape"), END_STONE_CAPE);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "opal_sword"), OPAL_SWORD);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "opal_sword_tip"), OPAL_SWORD_TIP);
        System.out.println("[End Goblin Traders] Registered Items");
    }
}
