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
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;


@Deprecated
@SuppressWarnings("deprecation")
public class ModOres {
//    private static ConfiguredFeature<?, ?> ORE_OPAL_END = Feature.ORE
//            .configure(new OreFeatureConfig(
//                    new BlockMatchRuleTest(Blocks.END_STONE),
//                    ModBlocks.OPAL_ORE.getDefaultState(),
//                    1)) // Vein size
//            .range(new RangeDecoratorConfig(
//                    // You can also use one of the other height providers if you don't want a uniform distribution
//                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(45)))) // Inclusive min and max height
//            .spreadHorizontally()
//            .repeat(1); // Number of veins per chunk

    @Deprecated
    public static void registerOres() {
//        if (EGobT.config.GENERATE_OPAL_ORE) {
//            RegistryKey<ConfiguredFeature<?, ?>> oreOpalEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
//                    new Identifier("endgoblintraders", "ore_opal_end"));
//            Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOpalEnd.getValue(), ORE_OPAL_END);
//            BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, oreOpalEnd);
//        }
    }
}
