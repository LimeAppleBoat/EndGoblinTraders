package com.jab125.egt.datagen;

import com.affehund.voidtotem.VoidTotem;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jab125.egt.EGobT;
import com.jab125.egt.init.ModBlocks;
import com.jab125.egt.init.ModEnchantments;
import com.jab125.egt.init.ModEntities;
import com.jab125.egt.init.ModItems;
import com.jab125.thonkutil.api.datagen.ThonkUtilLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.data.DataProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.data.server.BlockTagProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.IOException;
import java.util.function.Consumer;

import static net.minecraft.data.server.RecipeProvider.conditionsFromItem;

public class DataGeneration implements DataGeneratorEntrypoint {
    /**
     * Register {@link DataProvider} with the {@link FabricDataGenerator} during this entrypoint.
     *
     * @param generator The {@link FabricDataGenerator} instance
     */
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.addProvider(new EndGoblinTradeProvider(generator));
        generator.addProvider(new FabricBlockLootTableProvider(generator) {
            @Override
            protected void generateBlockLootTables() {
                this.addDrop(ModBlocks.OPAL_ORE, (blockx) -> {
                    return BlockLootTableGenerator.oreDrops(blockx, ModItems.OPAL);
                });
                this.addDrop(ModBlocks.OPAL_BLOCK);
            }
        });
        generator.addProvider(new FabricModelProvider(generator) {
            @Override
            public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OPAL_ORE);
                blockStateModelGenerator.registerParentedItemModel(ModBlocks.OPAL_ORE.asItem(), new Identifier("endgoblintraders:block/end_opal_ore"));
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.OPAL_BLOCK);
                blockStateModelGenerator.registerParentedItemModel(ModBlocks.OPAL_BLOCK.asItem(), new Identifier("endgoblintraders:block/opal_block"));
            }

            @Override
            public void generateItemModels(ItemModelGenerator itemModelGenerator) {
                itemModelGenerator.register(ModItems.ENCHANTED_GOLDEN_CARROT, Models.GENERATED);
                itemModelGenerator.register(ModItems.OPAL, Models.GENERATED);
                //itemModelGenerator.register(ModItems.END_GOBLIN_TRADER_SPAWN_EGG, Models.);
            }
        });
        generator.addProvider(new LanguageGen(generator, "en_us"));
        generator.addProvider(new LanguageGen(generator, "en_au"));
        generator.addProvider(new FabricRecipeProvider(generator) {
            @Override
            protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
                ShapedRecipeJsonBuilder.create(ModItems.DURABILITY_VOID_TOTEM)
                        .input('c', Items.CHORUS_FRUIT)
                        .input('e', Items.ENDER_EYE)
                        .input('E', Items.EMERALD)
                        .input('t', ModItems.DURABILITY_TOTEM).group(ModItems.DURABILITY_VOID_TOTEM.thonkutil$getId().getPath()).pattern("cec").pattern("EtE").pattern(" e ").criterion("has_totem", conditionsFromItemPredicates(ItemPredicate.Builder.create().tag(EGobT.VOID_DURABILITY_TOTEM).build(),
                                new ItemPredicate() {
                            @Override public JsonElement toJson() {var q = super.toJson();if (q.isJsonObject()) {JsonObject a = (JsonObject) q;a.addProperty("endgoblintraders:mod_installed", "voidtotem");return a;}return q;
                            }}))
                        .offerTo(exporter);

                DataGeneration.this.offerReversibleCompactingRecipes(exporter, ModItems.OPAL, ModBlocks.OPAL_BLOCK);
            }
        });
        generator.addProvider(new FabricTagProvider.BlockTagProvider(generator) {
            @Override
            protected void generateTags() {
                this.getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(ModBlocks.OPAL_BLOCK.thonkutil$getId(), "Data Gen");
                this.getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(ModBlocks.OPAL_ORE.thonkutil$getId(), "Data Gen");
                this.getTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.OPAL_BLOCK.thonkutil$getId(), "Data Gen");
                this.getTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.OPAL_ORE.thonkutil$getId(), "Data Gen");
            }
        });
        generator.addProvider(new FabricTagProvider.ItemTagProvider(generator) {

            @Override
            protected void generateTags() {
                this.getOrCreateTagBuilder(EGobT.VOID_DURABILITY_TOTEM).add(Items.TOTEM_OF_UNDYING, Items.ENDER_EYE, Items.CHORUS_FRUIT, ModItems.DURABILITY_TOTEM, ModItems.DURABILITY_VOID_TOTEM).addOptional(VoidTotem.VOID_TOTEM_ITEM.thonkutil$getId());
            }
        });

        //generator.addProvider(new LanguageGen(generator, "zh_cn")); not currently data generated
    }

    private static class LanguageGen extends ThonkUtilLanguageProvider {

        public LanguageGen(FabricDataGenerator fabricDataGenerator, String locale) {
            super(fabricDataGenerator, locale);
        }

        @Override
        protected void addTranslations() {
            switch (getLocale()) {
                case "en_us", "en_au" -> {

                    addAutoConfigTitle("End Goblin Traders (<RR> = Requires Game Restart)");

                    addAutoConfigCategory("default", "Misc");
                    addAutoConfigCategory("end_goblin_trader_config", "End Goblin Trader");

                    addAutoConfigOption("GENERATE_OPAL_ORE", "Generate Opal Ore <RR>");

                    addAutoConfigOption("END_GOBLIN_TRADER_CONFIG.THE_OVERWORLD_SETTINGS", "The Overworld Settings <RR>");
                    addAutoConfigOption("END_GOBLIN_TRADER_CONFIG.THE_NETHER_SETTINGS", "The Nether Settings <RR>");
                    addAutoConfigOption("END_GOBLIN_TRADER_CONFIG.THE_END_SETTINGS", "The End Settings <RR>");

                    addAutoConfigOption("END_GOBLIN_TRADER_CONFIG.END_GOBLIN_TRADER_ALT_TEXTURE", "End Goblin Traders use ragna goblin texture");
                    addAutoConfigOption("END_GOBLIN_TRADER_CONFIG.HIT_BACK", "Hit Back");

                    for (String dimension : new String[]{"THE_OVERWORLD","THE_NETHER","THE_END"}) {
                        addAutoConfigOption("END_GOBLIN_TRADER_CONFIG." + dimension + "_SETTINGS.MIN_HEIGHT", "Min Height");
                        addAutoConfigOption("END_GOBLIN_TRADER_CONFIG." + dimension + "_SETTINGS.MAX_HEIGHT", "Max Height");
                        addAutoConfigOption("END_GOBLIN_TRADER_CONFIG." + dimension + "_SETTINGS.CAN_SPAWN", "Can Spawn");
                        addAutoConfigOption("END_GOBLIN_TRADER_CONFIG." + dimension + "_SETTINGS.SPAWN_DELAY", "Spawn Delay");
                        addAutoConfigOption("END_GOBLIN_TRADER_CONFIG." + dimension + "_SETTINGS.SPAWN_CHANCE", "Spawn Chance");
                    }
                    add(ModItems.DURABILITY_VOID_TOTEM, "Void Totem?");
                    add(ModItems.DURABILITY_VOID_TOTEM.getTranslationKey() + ".disabled", "Install Void Totem by Affehund to use this item.");
                    add(ModItems.MYSTERY_ITEM, "Mystery Item");
                    add(ModItems.MYSTERY_ITEM.getTranslationKey() + ".desc", "Might have a use one day...");
                    add(ModItems.ENCHANTED_GOLDEN_CARROT, "Enchanted Golden Carrot");
                    add(ModItems.TELEPORT_TO_SPAWN_SPELL, "Teleport Spell");
                    add(ModItems.TELEPORT_TO_SPAWN_SPELL.getTranslationKey() + ".desc", "Might work, might not.");
                    add(ModItems.OPAL, "Opal");
                    add(ModItems.END_GOBLIN_TRADER_SPAWN_EGG, "End Goblin Trader Spawn Egg");
                    add(ModItems.END_STONE_CAPE, "End Stone Cape");
                    add("endgoblintraders.quest_item", "Quest Item"); // unused
                    // Blocks
                    add(ModBlocks.OPAL_ORE, "End Opal Ore");
                    add(ModBlocks.OPAL_ORE.getTranslationKey() + ".disabled", "You can still obtain opal from trades.");
                    add(ModBlocks.OPAL_BLOCK, "Opal Block");
                    // Entities
                    add(ModEntities.END_GOBLIN_TRADER, "End Goblin Trader");
                    // Maps
                    add("filled_map.endcity", "End Explorer Map");
                    add("filled_map.end_fountain", "End Fountain Map");
                    add("filled_map.stronghold", "Stronghold Explorer Map");
                    //
                    add(ModEnchantments.OPAL_INFUSED, "Opal Infused");
                }
            }
        }
    }

    public void offerReversibleCompactingRecipes(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible compacted) {
        ShapelessRecipeJsonBuilder.create(input, 9).group(input.asItem().thonkutil$getId().toString()).input(compacted).criterion("has_" + compacted.asItem().thonkutil$getId().getPath(), conditionsFromItem(compacted)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(compacted).group(compacted.asItem().thonkutil$getId().toString()).pattern("###").pattern("###").pattern("###").input('#', input).criterion("has_" + input.asItem().thonkutil$getId().getPath(), conditionsFromItem(input)).offerTo(exporter);
    };
}
