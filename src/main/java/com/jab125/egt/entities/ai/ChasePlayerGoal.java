package com.jab125.egt.entities.ai;

import com.jab125.egt.entities.EndGoblinTraderEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class ChasePlayerGoal extends Goal {
    private final EndGoblinTraderEntity endergoblin;
    private LivingEntity target;

    public ChasePlayerGoal(EndGoblinTraderEntity endergoblin) {
        this.endergoblin = endergoblin;
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
    }

    public boolean canStart() {
        this.target = this.endergoblin.getTarget();
        if (!(this.target instanceof PlayerEntity)) {
            return false;
        } else {
            double d = this.target.squaredDistanceTo(this.endergoblin);
            //return !(d > 256.0D) && this.endergoblin.getIsPlayerStaring((PlayerEntity) this.target);
            return false;
        }
    }

    public void start() {
        this.endergoblin.getNavigation().stop();
    }

    public void tick() {
        this.endergoblin.getLookControl().lookAt(this.target.getX(), this.target.getEyeY(), this.target.getZ());
    }
}
