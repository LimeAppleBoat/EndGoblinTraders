package com.jab125.egt.init;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
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
        if (biome.getCategory().equals(Biome.Category.THEEND) && true) {
            addMobSpawnToBiome(biome, SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.END_GOBLIN_TRADER, 35, 1, 2));
        }
    }
    private static void addMobSpawnToBiome(Biome biome, SpawnGroup classification, SpawnSettings.SpawnEntry... spawners) {
        convertImmutableSpawners(biome);
        List<SpawnSettings.SpawnEntry> spawnersList = new ArrayList<>(biome.getSpawnSettings().spawners.get(classification).getEntries());
        spawnersList.addAll(Arrays.asList(spawners));
        biome.getSpawnSettings().spawners.put(classification, Pool.of(spawnersList));
    }

    private static void convertImmutableSpawners(Biome biome) {
        if (biome.getSpawnSettings().spawners instanceof ImmutableMap) {
            biome.getSpawnSettings().spawners = new HashMap<>(biome.getSpawnSettings().spawners);
        }
    }
}
