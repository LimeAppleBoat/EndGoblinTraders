package com.jab125.egt.init;

import com.google.common.collect.ImmutableMap;
import com.jab125.egt.EGobT;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.MinecraftVersion;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ModSpawns {
    public static void init() {
        for (Biome biome : BuiltinRegistries.BIOME) {
            addSpawnEntries(biome);
        }
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, identifier, biome) -> addSpawnEntries(biome));
    }
    private static void addSpawnEntries(Biome biome) {
        if (biome.getCategory().equals(Biome.Category.THEEND) && EGobT.config.END_GOBLIN_CAN_SPAWN_IN_END) {
            addMobSpawnToBiome(biome, SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.END_GOBLIN_TRADER, EGobT.config.END_GOBLIN_SPAWN_RATE, 1, EGobT.config.END_GOBLIN_GROUP_SIZE));
        }
        if (biome.getCategory().equals(Biome.Category.NETHER) && EGobT.config.END_GOBLIN_CAN_SPAWN_IN_HELL) {
            addMobSpawnToBiome(biome, SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.END_GOBLIN_TRADER, EGobT.config.END_GOBLIN_SPAWN_RATE_HELL, 1, EGobT.config.END_GOBLIN_GROUP_SIZE_HELL));
        }
        if ((biome.getCategory().equals(part2caves()) || biome.getCategory().equals(Biome.Category.DESERT) || biome.getCategory().equals(Biome.Category.PLAINS)) && EGobT.config.END_GOBLIN_CAN_SPAWN_IN_OVERWORLD) {
            addMobSpawnToBiome(biome, SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.END_GOBLIN_TRADER, EGobT.config.END_GOBLIN_SPAWN_RATE_OVERWORLD, 1, EGobT.config.END_GOBLIN_GROUP_SIZE_OVERWORLD));
        }
    }
    private static void addMobSpawnToBiome(Biome biome, SpawnGroup classification, SpawnSettings.SpawnEntry... spawners) {
        convertImmutableSpawners(biome);
        List<SpawnSettings.SpawnEntry> spawnersList = new ArrayList<>(biome.getSpawnSettings().spawners.get(classification).getEntries());
        biome.getSpawnSettings().spawners.put(classification, Pool.of(spawnersList));
    }

    private static void convertImmutableSpawners(Biome biome) {
        if (biome.getSpawnSettings().spawners instanceof ImmutableMap) {
            biome.getSpawnSettings().spawners = new HashMap<>(biome.getSpawnSettings().spawners);
        }
    }

    private static Biome.Category part2caves() {
        if (MinecraftVersion.GAME_VERSION.getReleaseTarget().equals("1.18") || MinecraftVersion.GAME_VERSION.getReleaseTarget().startsWith("1.18.")) {
            return Biome.Category.UNDERGROUND;
        } else {
            return null;
        }
    }
}
