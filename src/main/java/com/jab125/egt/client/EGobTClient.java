package com.jab125.egt.client;

import com.jab125.egt.entities.EndGoblinTraderRenderer;
import com.jab125.egt.init.ModEntities;
import com.jab125.egt.init.ModItems;
import com.jab125.egt.item.OpalSwordTipTooltipData;
import com.jab125.egt.item.client.OpalSwordTipTooltipComponent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.hat.gt.entities.GoblinTraderModel;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

import static com.jab125.egt.util.VoidTotemQuarantine.showTooltip;
import static com.jab125.thonkutil.util.Util.isModInstalled;

@Environment(EnvType.CLIENT)
public class EGobTClient implements ClientModInitializer {
    public static final EntityModelLayer END_GOBLIN_MODEL_LAYER = new EntityModelLayer(new Identifier("endgoblintraders", "egoblin"), "goblin_render_layer");

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(ModItems.OPAL_SWORD, new Identifier("has_potion"), (itemStack, clientWorld, livingEntity, provider) -> PotionUtil.getPotionEffects(itemStack).size() == 0 ? 0.0F : 1.0F);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (PotionUtil.getPotionEffects(stack).size() == 0) return -1;
            return tintIndex == 0 ? PotionUtil.getColor(stack) : -1;
        }, ModItems.OPAL_SWORD);

        TooltipComponentCallback.EVENT.register((data -> {
            if (data instanceof OpalSwordTipTooltipData) {
                System.out.println("O_O");
                return new OpalSwordTipTooltipComponent();
            }
            return null;
        }));
        ItemTooltipCallback.EVENT.register(EGobTClient::getTooltip);
        EntityRendererRegistry.register(ModEntities.END_GOBLIN_TRADER, EndGoblinTraderRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(END_GOBLIN_MODEL_LAYER, GoblinTraderModel::getTexturedModelData);
    }

    private static void getTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip) {
        Item item = stack.getItem();
        if (item.equals(ModItems.DURABILITY_VOID_TOTEM)) {
            if (isModInstalled("voidtotem") && showTooltip()) {
                tooltip.add(new TranslatableText("tooltip.voidtotem.totem_of_void_undying").formatted(Formatting.GREEN));
            }
            if (!isModInstalled("voidtotem")) {
                tooltip.add(new TranslatableText(ModItems.DURABILITY_VOID_TOTEM.getTranslationKey() + ".disabled").formatted(Formatting.GRAY));
            }
        }
    }
}
