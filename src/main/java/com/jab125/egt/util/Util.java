package com.jab125.egt.util;

import net.fabricmc.loader.api.FabricLoader;

public class Util {
    public static boolean field_2983_f() {
        @SuppressWarnings("OptionalGetWithoutIsPresent") String versionString = FabricLoader.getInstance().getModContainer("endgoblintraders").get().getMetadata().getVersion().getFriendlyString();;
        Version version = new Version(versionString);
        if (version.compareTo(new Version("1.1.0")) >= 0) {
            return true;
        }
        return false;
    }
}
