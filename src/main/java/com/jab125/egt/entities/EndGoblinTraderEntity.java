package com.jab125.egt.entities;

import com.jab125.egt.entities.ai.AttackRevengeTargetGoal;
import com.jab125.egt.entities.ai.ChasePlayerGoal;
import com.jab125.egt.init.ModTrades;
import net.hat.gt.GobT;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.hat.gt.entities.ai.*;
import net.hat.gt.init.ModPotions;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.TippedArrowRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EndGoblinTraderEntity extends AbstractGoblinEntity {
    public EndGoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

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
    public ItemStack getFavouriteFood() {
        ItemStack itemStack = new ItemStack(Items.POTION);
        PotionUtil.setPotion(itemStack, ModPotions.POWERFUL_INSTANT_HEALTH);
        return itemStack;
    }

    @Override
    public boolean canAttackBack() {
        return GobT.config.GOBLIN_HIT_BACK;
    }

    @Override
    public boolean canSwimToFood() {
        return false;
    }

    @Override
    protected void fillRecipes() {
        TradeOffers.Factory[] factorys = ModTrades.END_GOBLIN_TRADER_TRADES.get(1);
        TradeOffers.Factory[] factorys2 = ModTrades.END_GOBLIN_TRADER_TRADES.get(2);
        TradeOffers.Factory[] factorys3 = ModTrades.END_GOBLIN_TRADER_TRADES.get(3);
        TradeOffers.Factory[] factorys4 = ModTrades.END_GOBLIN_TRADER_TRADES.get(4);
        if (factorys != null && factorys2 != null && factorys3 != null) {
            TradeOfferList tradeOfferList = this.getOffers();
            this.fillRecipesFromPool(tradeOfferList, factorys, ThreadLocalRandom.current().nextInt(4, 6 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys2, ThreadLocalRandom.current().nextInt(3, 5 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys3, ThreadLocalRandom.current().nextInt(1, 3 + 1));
            this.fillRecipesFromPool(tradeOfferList, factorys4, ThreadLocalRandom.current().nextInt(25, 251 + 1));
            }

    }

    @Override
    public void tick(){
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
        return spawnReason == SpawnReason.SPAWNER || ThreadLocalRandom.current().nextInt(1, GobT.config.VEIN_GOBLIN_SPAWN_RATE_D + 1) == 1;
    }

    boolean isPlayerStaring(PlayerEntity player) {
        ItemStack itemStack = (ItemStack)player.getInventory().armor.get(3);
        if (itemStack.isOf(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        } else {
            Vec3d vec3d = player.getRotationVec(1.0F).normalize();
            Vec3d vec3d2 = new Vec3d(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
            double d = vec3d2.length();
            vec3d2 = vec3d2.normalize();
            double e = vec3d.dotProduct(vec3d2);
            return e > 1.0D - 0.025D / d && player.canSee(this);
        }
    }

    public boolean getIsPlayerStaring(PlayerEntity playerEntity) {
        return isPlayerStaring(playerEntity);
    }

    @Override
    public boolean isWet() {
        return this.isTouchingWater();
    }

    public boolean hurtByWater() {
        return true;
    }

}
