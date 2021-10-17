package com.jab125.egt;

import com.jab125.egt.config.EndGoblinTradersConfig;
import com.jab125.egt.init.ModEntities;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.hat.gt.config.GoblinTradersConfig;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EGobT implements ModInitializer {
	public static final String MODID = "endgoblintraders";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static EndGoblinTradersConfig config;

	@Override
	public void onInitialize() {
		AutoConfig.register(EndGoblinTradersConfig.class, Toml4jConfigSerializer::new);
		config = AutoConfig.getConfigHolder(EndGoblinTradersConfig.class).getConfig();
		ModEntities.registerEntities();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}

	public static Identifier endGoblinTexture() {

		if (config.END_GOBLIN_ALT_TEXTURE) {
			return EGobT.id("textures/entity/endgoblintrader/ragna_goblin.png");
		} else {
			return EGobT.id("textures/entity/endgoblintrader/end_goblin_trader.png");
		}
	}
}
