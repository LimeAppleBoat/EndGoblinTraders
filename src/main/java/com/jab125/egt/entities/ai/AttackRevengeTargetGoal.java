package com.jab125.egt.entities.ai;

import net.hat.gt.entities.AbstractGoblinEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Hand;

public class AttackRevengeTargetGoal extends net.hat.gt.entities.ai.AttackRevengeTargetGoal {
    public AttackRevengeTargetGoal(AbstractGoblinEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        LivingEntity revengeTarget = this.entity.getAttacker();
        if (revengeTarget != null && this.entity.getCurrentCustomer() == null) {
            this.entity.getLookControl().lookAt(revengeTarget, 10.0F, (float)this.entity.getHeadRollingTimeLeft());
            if ((double)this.entity.distanceTo(revengeTarget) >= 25.0D) {
                this.entity.getNavigation().startMovingTo(revengeTarget, 0.5D);
            } else {
                revengeTarget.damage(DamageSource.mob(this.entity), 3.0F);
                this.entity.swingHand(Hand.MAIN_HAND);
                //this.entity.setAttacker((LivingEntity)null);
            }
        }

    }
}
