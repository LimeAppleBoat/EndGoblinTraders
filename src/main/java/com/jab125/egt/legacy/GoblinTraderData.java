package com.jab125.egt.legacy;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Reworked for fabric
 */
public class GoblinTraderData extends PersistentState
{
    protected static final String DATA_NAME = "egobt"+"_goblin_trader";

    protected final Map<String, GoblinData> data = new HashMap<>();

    public GoblinTraderData() {}

    public void clear() {
        data.clear();
    }

    public GoblinData getGoblinData(String key)
    {
        return this.data.computeIfAbsent(key, s -> new GoblinData(this));
    }

    public GoblinTraderData read(NbtCompound tag)
    {
        if(tag.contains("GoblinTraderSpawnDelay", NbtElement.INT_TYPE))
        {
            this.getGoblinData("GoblinTrader").setGoblinTraderSpawnDelay(tag.getInt("GoblinTraderSpawnDelay"));
        }
        if(tag.contains("GoblinTraderSpawnChance", NbtElement.INT_TYPE))
        {
            this.getGoblinData("GoblinTrader").setGoblinTraderSpawnChance(tag.getInt("GoblinTraderSpawnChance"));
        }
        if(tag.contains("Data", NbtElement.LIST_TYPE))
        {
            this.data.clear();
            NbtList list = tag.getList("Data", NbtElement.COMPOUND_TYPE);
            list.forEach(nbt -> {
                NbtCompound goblinTag = (NbtCompound) nbt;
                String key = goblinTag.getString("Key");
                GoblinData data = new GoblinData(this);
                data.read(goblinTag);
                this.data.put(key, data);
            });
        }
        return this;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound compound)
    {
        NbtList list = new NbtList();
        this.data.forEach((s, goblinData) -> {
            NbtCompound goblinTag = new NbtCompound();
            goblinData.write(goblinTag);
            goblinTag.putString("Key", s);
            list.add(goblinTag);
        });
        compound.put("Data", list);
        return compound;
    }

    public static GoblinTraderData get(MinecraftServer server)
    {
        ServerWorld level = server.getWorld(World.OVERWORLD);
        assert level != null;
        return level.getPersistentStateManager().getOrCreate(tag -> new GoblinTraderData().read(tag), GoblinTraderData::new, DATA_NAME);
    }
}