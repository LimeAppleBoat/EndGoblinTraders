package com.jab125.egt.util;

import com.terraformersmc.modmenu.util.mod.Mod;
import com.terraformersmc.modmenu.util.mod.fabric.FabricMod;
import net.fabricmc.loader.api.FabricLoader;

public class Util {
    public static boolean field_2983_f() {
        String versionString = new FabricMod(FabricLoader.getInstance().getModContainer("endgoblintraders").get()).getVersion();
        Version version = new Version(versionString);
        if (version.compareTo(new Version("1.1.0")) >= 0) {
            return true;
        }
        return false;
    }
}
