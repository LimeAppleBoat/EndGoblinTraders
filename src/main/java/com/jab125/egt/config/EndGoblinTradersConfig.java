package com.jab125.egt.config;

import com.jab125.egt.EGobT;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.hat.gt.GobT;
import net.hat.gt.config.GoblinTradersConfig;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

@Config(name = EGobT.MODID)
@Config.Gui.CategoryBackground(category = "end_goblin_trader_config", background = "textures/block/end_stone.png")
public class EndGoblinTradersConfig implements ConfigData {
    public boolean GENERATE_OPAL_ORE = true;

    @ConfigEntry.Category("end_goblin_trader_config")
    @ConfigEntry.Gui.TransitiveObject
    public EndGoblinTrader END_GOBLIN_TRADER_CONFIG = new EndGoblinTrader();

    public static class EndGoblinTrader {
        // Settings
        public boolean END_GOBLIN_TRADER_ALT_TEXTURE = false;
        public boolean HIT_BACK = true;
        @ConfigEntry.Gui.Excluded
        public int END_GOBLIN_SPAWN_RATE_D = 1;

        @ConfigEntry.Gui.CollapsibleObject
        public DimensionSettings THE_OVERWORLD_SETTINGS = new DimensionSettings(DimensionType.OVERWORLD.getMinimumY(), 255, true, 10, 48000);
        @ConfigEntry.Gui.CollapsibleObject
        public DimensionSettings THE_NETHER_SETTINGS = new DimensionSettings(0, 127, true, 25, 32000);
        @ConfigEntry.Gui.CollapsibleObject
        public DimensionSettings THE_END_SETTINGS = new DimensionSettings(0, 255, true, 75, 24000);
    }
    public static class DimensionSettings {
        public int MIN_HEIGHT;
        public int MAX_HEIGHT;
        public boolean CAN_SPAWN;
        public int SPAWN_CHANCE;
        public int SPAWN_DELAY;
        public DimensionSettings(int minHeight, int maxHeight, boolean canSpawn, int spawnChance, int spawnDelay) {
            this.MIN_HEIGHT = minHeight;
            this.MAX_HEIGHT = maxHeight;
            this.CAN_SPAWN = canSpawn;
            this.SPAWN_CHANCE = spawnChance;
            this.SPAWN_DELAY = spawnDelay;
        }
    }

    @Override
    public void validatePostLoad() throws ValidationException {
        ConfigData.super.validatePostLoad();
        if (this.GENERATE_OPAL_ORE) {
            this.GENERATE_OPAL_ORE = false;
        }
    }
}
