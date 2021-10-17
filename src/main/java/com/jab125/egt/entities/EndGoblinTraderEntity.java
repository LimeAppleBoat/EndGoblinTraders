package com.jab125.egt.entities;

import net.hat.gt.GobT;
import net.hat.gt.entities.AbstractGoblinEntity;
import net.hat.gt.init.ModPotions;
import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.TippedArrowRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EndGoblinTraderEntity extends AbstractGoblinEntity {
    public EndGoblinTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getFavouriteFood() {
        ItemStack itemStack = new ItemStack(Items.POTION);
        PotionUtil.setPotion(itemStack, ModPotions.POWERFUL_INSTANT_HEALTH);
        return itemStack;
    }

    @Override
    public boolean canAttackBack() {
        return false;
    }

    @Override
    public boolean canSwimToFood() {
        return true;
    }

    @Override
    protected void fillRecipes() {

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
}
