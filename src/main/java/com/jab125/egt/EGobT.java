package com.jab125.egt;

import com.jab125.egt.compat.voidtotem.VoidTotemEvent;
import com.jab125.egt.config.EndGoblinTradersConfig;
import com.jab125.egt.init.*;
import com.jab125.egt.recipes.OpalSwordRecipe;
import com.jab125.limeappleboat.gobt.api.GobTEvents;
import com.jab125.thonkutil.api.events.EventTaxi;
import com.jab125.util.tradehelper.TradeManager;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.hat.gt.spawning.GoblinTraderSpawner;
import net.hat.gt.spawning.SpawnHandler;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import static com.jab125.thonkutil.util.Util.isModInstalled;

public class EGobT implements ModInitializer {
    public static final TagKey<Item> VOID_DURABILITY_TOTEM = TagKey.of(Registry.ITEM_KEY, new Identifier("endgoblintraders", "void_durability_totem"));
    public static final String MODID = "endgoblintraders";
    public static final ItemStack QUEST_ITEM = createQuestItem();
    public static final SpecialRecipeSerializer<OpalSwordRecipe> OPAL_SWORD_RECIPE = new SpecialRecipeSerializer<>(OpalSwordRecipe::new);
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static EndGoblinTradersConfig config;

    private static ItemStack createQuestItem() {
        ItemStack questItem = new ItemStack(Items.PAPER);
        questItem.setCustomName(Text.translatable("endgoblintraders.quest_item"));
        questItem.addEnchantment(null, 0);
        return questItem;
    }

    @Override
    public void onInitialize() {
        Registry.register(Registry.RECIPE_SERIALIZER, id("opal_sword_potion"), OPAL_SWORD_RECIPE);
        System.out.println(Registry.RECIPE_SERIALIZER.getId(OPAL_SWORD_RECIPE) + ", " + Registry.RECIPE_SERIALIZER.get(new Identifier("opal_sword_potion")));
        //System.exit(0);
        EventTaxi.registerEventTaxiSubscriber(ModEvents.class);
        if (isModInstalled("voidtotem")) {
            EventTaxi.registerEventTaxiSubscriber(VoidTotemEvent.class);
            VoidTotemEvent.voidTotemEvent();
        }
        TradeManager.instance().registerTrader(ModEntities.END_GOBLIN_TRADER);
        AutoConfig.register(EndGoblinTradersConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(EndGoblinTradersConfig.class).getConfig();
        ModEntities.registerEntities();
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModOres.registerOres();
        ModSounds.registerSounds();
        ModPotions.registerPotions();
        ModEnchantments.registerEnchantments();
        final LambdaFix<Boolean> loaded = new LambdaFix<>(false);
        ServerWorldEvents.LOAD.register((minecraftServer, world) -> {
            if (loaded.get()) return;
            SpawnHandler.addToSpawners(DimensionTypes.THE_NETHER.getValue(), new GoblinTraderSpawner(minecraftServer, "EndGoblinTraderHell", ModEntities.END_GOBLIN_TRADER, Objects.requireNonNull(ModEntities.END_GOBLIN_TRADER.create(world))));
            SpawnHandler.addToSpawners(DimensionTypes.THE_END.getValue(), new GoblinTraderSpawner(minecraftServer, "EndGoblinTraderSky", ModEntities.END_GOBLIN_TRADER, Objects.requireNonNull(ModEntities.END_GOBLIN_TRADER.create(world))));
            SpawnHandler.addToSpawners(DimensionTypes.OVERWORLD.getValue(), new GoblinTraderSpawner(minecraftServer, "EndGoblinTraderEarth", ModEntities.END_GOBLIN_TRADER, Objects.requireNonNull(ModEntities.END_GOBLIN_TRADER.create(world))));
            SpawnHandler.getSpawners().forEach((identifier, goblinTraderSpawner) -> {
                //System.out.println(identifier.toString() + ": " + goblinTraderSpawner);
            });
            loaded.set(true);
        });
        GobTEvents.ON_ATTEMPT_SPAWN.register(((entityType, serverWorld, blockPos) -> {
            ActionResult result = ActionResult.PASS;
            if (entityType.equals(ModEntities.END_GOBLIN_TRADER) && serverWorld.getDimensionKey().equals(DimensionTypes.OVERWORLD)) {
                /* End Goblin Traders can spawn up to y=255, this is just a test to make sure they don't spawn in broad daylight */
                if (!(serverWorld.getLightLevel(blockPos) < 5 && !serverWorld.isSkyVisible(blockPos)))
                    result = ActionResult.FAIL;
            }
			/*
			  Prevents End Goblin Traders from spawning the central end island
			 */
            BlockPos pos = new BlockPos(blockPos.getX(), 90, blockPos.getZ());
            if (entityType.equals(ModEntities.END_GOBLIN_TRADER) && serverWorld.getDimensionKey().equals(DimensionTypes.THE_END)) {
                if (pos.isWithinDistance(new BlockPos(0, 90, 0), 750)) result = ActionResult.FAIL;
            }
			/*
			  Prevents End Goblin Traders from spawning on Bedrock
			 */
            if (entityType.equals(ModEntities.END_GOBLIN_TRADER) && serverWorld.getDimensionKey().equals(DimensionTypes.THE_END)) {
                if (serverWorld.getBlockState(blockPos.down()).getBlock().equals(Blocks.BEDROCK) && (entityType.equals(ModEntities.END_GOBLIN_TRADER)))
                    result = ActionResult.FAIL;
            }
            return result;
        }));
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        //LOGGER.info("Hello Fabric world!");

        //ResourceManagerHelper.registerBuiltinResourcePack(id("gobtvanillaish"), Objects.requireNonNull(FabricLoader.getInstance().getModContainer(MODID)).get(), ResourcePackActivationType.NORMAL);
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    public static Identifier endGoblinTexture() {

        if (EGobT.config.END_GOBLIN_TRADER_CONFIG.END_GOBLIN_TRADER_ALT_TEXTURE) {
            return EGobT.id("textures/entity/endgoblintrader/ragna_goblin.png");
        } else {
            return EGobT.id("textures/entity/endgoblintrader/end_goblin_trader.png");
        }
    }
}
