package com.jab125.egt.init;

import com.jab125.egt.EGobT;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModOres {
    private static final ConfiguredFeature<?, ?> ORE_OPAL_END = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.OPAL_ORE.getDefaultState(),
                    3));

    public static final PlacedFeature OPAL_ORE_END_PLACED_FEATURE = ORE_OPAL_END.withPlacement(
            CountPlacementModifier.of(3), // number of veins per chunk
            SquarePlacementModifier.of(), // spreading horizontally
            HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.fixed(64))); // height

    public static void registerOres() {
        if (EGobT.config.GENERATE_OPAL_ORE) {
            RegistryKey<ConfiguredFeature<?, ?>> oreOpalEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                    new Identifier("endgoblintraders", "ore_opal_end"));
            Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("endgoblintraders", "ore_opal_end"),
                    OPAL_ORE_END_PLACED_FEATURE);
            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOpalEnd.getValue(), ORE_OPAL_END);
            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                    new Identifier("endgoblintraders", "ore_opal_end")));
        }
    }
}
