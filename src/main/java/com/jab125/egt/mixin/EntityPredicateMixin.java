package com.jab125.egt.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.jab125.thonkutil.util.Util.isModInstalled;

@Mixin(EntityPredicate.class)
public abstract class EntityPredicateMixin {
    @Unique
    private String endgoblintraders$modInstalledPredicate = null;

    @Inject(method = "test(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/entity/Entity;)Z", at = @At("RETURN"), cancellable = true)
    private void test(ServerWorld world, Vec3d pos, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        //System.out.println("PREDICATE:" + this.endgoblintraders$modInstalledPredicate);
        if (this.endgoblintraders$modInstalledPredicate != null && !isModInstalled(this.endgoblintraders$modInstalledPredicate)) {
            //System.out.println("TESTING");
            cir.setReturnValue(false);
        }
    }


    @Inject(method = "fromJson", at = @At(value = "RETURN", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void fromJson(JsonElement json, CallbackInfoReturnable<EntityPredicate> cir, JsonObject entityData) {
        //if (entityData.has("endgoblintraders:mod_installed"))
        //System.out.println(entityData.toString());
        var e = (EntityPredicateMixin) (Object) cir.getReturnValue();
        if (entityData.has("endgoblintraders:mod_installed")) {
            e.endgoblintraders$modInstalledPredicate = JsonHelper.getString(entityData, "endgoblintraders:mod_installed");
        }
    }
}