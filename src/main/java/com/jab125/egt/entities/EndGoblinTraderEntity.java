package com.jab125.egt.entities;

import com.jab125.egt.EGobT;
import com.jab125.egt.init.ModEntities;
import com.jab125.thonkutil.util.Util;
import com.mrcrayfish.goblintraders.Config;
import com.mrcrayfish.goblintraders.entity.AbstractGoblinEntity;
import com.mrcrayfish.goblintraders.entity.TraderCreatureEntity;
import com.mrcrayfish.goblintraders.init.ModPotions;
import com.mrcrayfish.goblintraders.trades.EntityTrades;
import com.mrcrayfish.goblintraders.trades.IRaritySettings;
import com.mrcrayfish.goblintraders.trades.TradeManager;
import com.mrcrayfish.goblintraders.trades.TradeRarity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EndGoblinTraderEntity extends AbstractGoblinEntity {
    private static NbtCompound PLAYER_QUESTS = new NbtCompound();

    public EndGoblinTraderEntity(EntityType<? extends TraderCreatureEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Override
    public Identifier getTexture() {
        return EGobT.endGoblinTexture();
    }

    @Override
    protected void populateTradeData() {
        TradeOfferList offers = this.getOffers();
        EntityTrades entityTrades = TradeManager.instance().getTrades(ModEntities.END_GOBLIN_TRADER);
        if (entityTrades != null) {
            Map<TradeRarity, List<TradeOffers.Factory>> tradeMap = entityTrades.getTradeMap();
            TradeRarity[] var4 = TradeRarity.values();
            int var5 = var4.length;

            for (TradeRarity rarity : var4) {
                IRaritySettings settings = Config.ENTITIES.goblinTrader.trades.getSettings(rarity); //too lazy
                if (!(settings.includeChance() <= 0.0) && (!(settings.includeChance() < 1.0) || !(this.getRandom().nextDouble() > settings.includeChance()))) {
                    List<TradeOffers.Factory> trades = tradeMap.get(rarity);
                    int min = Math.min(settings.getMinValue(), settings.getMaxValue());
                    int max = Math.max(settings.getMinValue(), settings.getMaxValue());
                    int count = min + this.getRandom().nextInt(max - min + 1);
                    this.addTrades(offers, trades, count, rarity.shouldShuffle());
                }
            }
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Quests")) {
            PLAYER_QUESTS = nbt.getCompound("Quests");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Quests", getPlayerQuests());
    }

    public static NbtCompound getPlayerQuests() {
        return PLAYER_QUESTS;
    }


//    @Override
//    protected SoundEvent getAmbientSound() {
//        return ModSounds.IDLE_GRUNT_ECHO;
//    }
//
//    @Override
//    protected SoundEvent getHurtSound(DamageSource source) {
//        return ModSounds.IDLE_GRUNT_ECHO;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        return ModSounds.IDLE_GRUNT_ECHO;
//    }
//
//    @Override
//    protected SoundEvent getTradingSound(boolean sold) {
//        return (sold ? ModSounds.IDLE_GRUNT_ECHO : ModSounds.ANNOYED_GRUNT_ECHO);
//    }
    //    @Override
//    protected void initGoals() {
//        this.goalSelector.add(0, new StunGoal(this));
//        this.goalSelector.add(1, new SwimGoal(this));
//        this.goalSelector.add(1, new FirePanicGoal(this, 0.5D));
//        this.goalSelector.add(2, new TradeWithPlayerGoal(this));
//        this.goalSelector.add(2, new LookAtCustomerGoal(this));
//        this.goalSelector.add(2, new AttackRevengeTargetGoal(this));
//        this.goalSelector.add(3, new EatFavouriteFoodGoal(this));
//        this.goalSelector.add(4, new FindFavouriteFoodGoal(this));
//        this.goalSelector.add(5, new TemptGoal(this, 0.44999998807907104D, Ingredient.ofItems(new ItemConvertible[]{this.getFavouriteFood().getItem()}), false));
//        this.goalSelector.add(6, new FollowPotentialCustomerGoal(this));
//        this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.35D));
//        this.goalSelector.add(8, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
//    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return super.interactMob(player, hand);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
    }

    @Override
    public ItemStack getFavouriteFood() {
        ItemStack itemStack = new ItemStack(Items.POTION);
        PotionUtil.setPotion(itemStack, ModPotions.POWERFUL_INSTANT_HEALTH);
        return itemStack;
    }

    @Override
    protected int getMaxRestockDelay() {
        return 0;
    }

    @Override
    public boolean canAttackBack() {
        //return GobT.config.GOBLIN_HIT_BACK;
        return EGobT.config.END_GOBLIN_TRADER_CONFIG.HIT_BACK;
    }


    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient) {
            this.updateLeash();
        }
        if (ThreadLocalRandom.current().nextInt(0, 2 + 1) == 2) {
            this.world.addParticle(ParticleTypes.PORTAL, this.getX() - 0.5 + this.getRandom().nextDouble(), this.getY() + 0.5 - 0.5 + this.getRandom().nextDouble(), this.getZ() - 0.5 + this.getRandom().nextDouble(), 0, 0, 0);
        }
    }

    @SuppressWarnings("unused") // Required for the query, IntelliJ marks it though.
    public static boolean canEndGoblinSpawn(EntityType<? extends MobEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos blockPos = pos.down();
        return spawnReason == SpawnReason.SPAWNER || ThreadLocalRandom.current().nextInt(1, EGobT.config.END_GOBLIN_TRADER_CONFIG.END_GOBLIN_SPAWN_RATE_D + 1) == 1;
    }

    @Override
    public boolean isWet() {
        return this.isTouchingWater() || this.world.getBlockState(this.getBlockPos()).isOf(Blocks.BUBBLE_COLUMN);
    }

    public boolean hurtByWater() {
        return true;
    }


    public int minSpawnHeight() {
        int minSpawnHeight = 0;
        if (!world.isClient() && world.getRegistryKey() == World.OVERWORLD)
            minSpawnHeight = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_OVERWORLD_SETTINGS.MIN_HEIGHT;
        if (!world.isClient() && world.getRegistryKey() == World.NETHER)
            minSpawnHeight = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_NETHER_SETTINGS.MIN_HEIGHT;
        if (!world.isClient() && world.getRegistryKey() == World.END)
            minSpawnHeight = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_END_SETTINGS.MIN_HEIGHT;
        return minSpawnHeight;
    }

    public int maxSpawnHeight() {
        int maxSpawnHeight = 0;
        if (!world.isClient() && world.getRegistryKey() == World.OVERWORLD)
            maxSpawnHeight = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_OVERWORLD_SETTINGS.MAX_HEIGHT;
        if (!world.isClient() && world.getRegistryKey() == World.NETHER)
            maxSpawnHeight = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_NETHER_SETTINGS.MAX_HEIGHT;
        if (!world.isClient() && world.getRegistryKey() == World.END)
            maxSpawnHeight = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_END_SETTINGS.MAX_HEIGHT;
        return maxSpawnHeight;
    }

    public int spawnDelay() {
        int spawnDelay = 2147483647;
        if (!world.isClient() && world.getRegistryKey() == World.OVERWORLD)
            spawnDelay = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_OVERWORLD_SETTINGS.SPAWN_DELAY;
        if (!world.isClient() && world.getRegistryKey() == World.NETHER)
            spawnDelay = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_NETHER_SETTINGS.SPAWN_DELAY;
        if (!world.isClient() && world.getRegistryKey() == World.END)
            spawnDelay = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_END_SETTINGS.SPAWN_DELAY;
        return spawnDelay;
    }

    public int spawnChance() {
        int spawnChance = 0;
        if (!world.isClient() && world.getRegistryKey() == World.OVERWORLD)
            spawnChance = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_OVERWORLD_SETTINGS.SPAWN_CHANCE;
        if (!world.isClient() && world.getRegistryKey() == World.NETHER)
            spawnChance = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_NETHER_SETTINGS.SPAWN_CHANCE;
        if (!world.isClient() && world.getRegistryKey() == World.END)
            spawnChance = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_END_SETTINGS.SPAWN_CHANCE;
        return spawnChance;
    }

    public boolean canSpawn() {
        boolean canSpawn = false;
        if (!world.isClient() && world.getRegistryKey() == World.OVERWORLD)
            canSpawn = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_OVERWORLD_SETTINGS.CAN_SPAWN;
        if (!world.isClient() && world.getRegistryKey() == World.NETHER)
            canSpawn = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_NETHER_SETTINGS.CAN_SPAWN;
        if (!world.isClient() && world.getRegistryKey() == World.END)
            canSpawn = EGobT.config.END_GOBLIN_TRADER_CONFIG.THE_END_SETTINGS.CAN_SPAWN;
        return canSpawn;
    }
}
