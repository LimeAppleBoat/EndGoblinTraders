package com.jab125.egt.init;

import com.jab125.egt.entities.EndGoblinTraderEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry.register;

public class ModEntities {

    public static final EntityType<EndGoblinTraderEntity> END_GOBLIN_TRADER =
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EndGoblinTraderEntity::new).dimensions(EntityDimensions.fixed(0.5f, 1.0f)).build();

    public static void registerEntities() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier("endgoblintraders", "end_goblin_trader"), END_GOBLIN_TRADER);
        register(END_GOBLIN_TRADER, EndGoblinTraderEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7D));
    }

}
