package com.jab125.egt.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block OPAL_ORE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).requiresTool().strength(7.0F, 12.0F));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("endgoblintraders", "end_opal_ore"), OPAL_ORE);
        Registry.register(Registry.ITEM, new Identifier("endgoblintraders", "end_opal_ore"), new BlockItem(OPAL_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
