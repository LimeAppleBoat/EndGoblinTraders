package com.jab125.egt.client;

import com.jab125.egt.entities.EndGoblinTraderRenderer;
import com.jab125.egt.init.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.hat.gt.entities.GoblinTraderModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EGobTClient implements ClientModInitializer {
    public static final EntityModelLayer END_GOBLIN_MODEL_LAYER = new EntityModelLayer(new Identifier("endgoblintraders", "egoblin"), "goblin_render_layer");

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.END_GOBLIN_TRADER, EndGoblinTraderRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(END_GOBLIN_MODEL_LAYER, GoblinTraderModel::getTexturedModelData);
    }
}
