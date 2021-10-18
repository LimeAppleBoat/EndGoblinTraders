package com.jab125.egt.config;

import com.jab125.egt.EGobT;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = EGobT.MODID)
public class EndGoblinTradersConfig implements ConfigData {
    // Settings
    public boolean END_GOBLIN_ALT_TEXTURE = false;
    public boolean END_GOBLIN_CAN_SPAWN_IN_END = true;
    public boolean END_GOBLIN_CAN_SPAWN_IN_OVERWORLD = true;
    public boolean END_GOBLIN_CAN_SPAWN_IN_HELL = true;
    public int END_GOBLIN_SPAWN_RATE = 35;
    public int END_GOBLIN_SPAWN_RATE_D = 1;
    public int END_GOBLIN_GROUP_SIZE = 2;

    public boolean D = true;
}
