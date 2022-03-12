package com.jab125.egt.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block OPAL_ORE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).requiresTool().strength(7.0F, 12.0F));
    public static final Block OPAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MapColor.PALE_GREEN).requiresTool().strength(16.0F, 18.0F).sounds(BlockSoundGroup.METAL));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("endgoblintraders", "end_opal_ore"), OPAL_ORE);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "end_opal_ore"), new BlockItem(OPAL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.BLOCK, new Identifier("endgoblintraders", "opal_block"), OPAL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "opal_block"), new BlockItem(OPAL_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
