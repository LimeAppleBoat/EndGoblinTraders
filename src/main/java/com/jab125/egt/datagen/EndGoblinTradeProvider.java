package com.jab125.egt.datagen;

import com.affehund.voidtotem.VoidTotem;
import com.jab125.egt.init.ModEnchantments;
import com.jab125.egt.init.ModEntities;
import com.jab125.egt.init.ModItems;
import com.jab125.egt.init.ModPotions;
import com.jab125.egt.legacy.ContainerTrade;
import com.mrcrayfish.goblintraders.datagen.TradeProvider;
import com.mrcrayfish.goblintraders.trades.TradeRarity;
import com.mrcrayfish.goblintraders.trades.type.BasicTrade;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataWriter;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.io.IOException;

public class EndGoblinTradeProvider extends TradeProvider {
    protected EndGoblinTradeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void registerTrades() {
        System.out.println("TRADES REGISTERED");
        this.registerEndGoblinTraderTrades();
    }

    public void registerEndGoblinTraderTrades() {
        registerCommonEndGoblinTrades();
        registerUncommonEndGoblinTrades();
        registerRareEndGoblinTrades();
        registerEpicEndGoblinTrades();
        registerLegendaryEndGoblinTrades();
    }


    public ItemStack itemFromPotion(Potion potion) {
        ItemStack potionStack = new ItemStack(Items.POTION);
        PotionUtil.setPotion(potionStack, potion);
        return potionStack;
    }

    public void registerCommonEndGoblinTrades() {
        /* ************************************************************************************** *
         *                                     COMMON                                             *
         * ************************************************************************************** */


        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ENDER_PEARL, 10))
                .setOfferStack(itemFromPotion(Potions.SLOW_FALLING))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(10)
                
                .setExperience(26)
                .build());


        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.END_STONE, 12))
                .setOfferStack(new ItemStack(Items.AZALEA))
                .setPriceMultiplier(0F)
                .setMaxTrades(128)
                
                .setExperience(2)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ENDER_PEARL, 16))
                .setOfferStack(new ItemStack(Items.PHANTOM_MEMBRANE))
                .setPriceMultiplier(0F)
                .setMaxTrades(164)
                
                .setExperience(2)
                .build());
    }

    public void registerUncommonEndGoblinTrades() {
        /* ************************************************************************************** *
         *                                     UNCOMMON                                           *
         * ************************************************************************************** */
    }

    public void registerRareEndGoblinTrades() {
        /* ************************************************************************************** *
         *                                      RARE                                              *
         * ************************************************************************************** */

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 3))
                .setOfferStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setExperience(40)
                .build());

        ItemStack knockbackStick = new ItemStack(Items.STICK);
        knockbackStick.setCustomName(Text.translatable(Enchantments.KNOCKBACK.getTranslationKey()).append(" ").append(Text.translatable(Items.STICK.getTranslationKey())));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 3))
                .setOfferStack(knockbackStick)
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setExperience(40)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.KNOCKBACK, 2))
                .build());

        var tip = new ItemStack(ModItems.OPAL_SWORD_TIP);
        var chest = new ItemStack(Items.CHEST);
        chest.setCustomName(Text.translatable("endgoblintraders.opal_sword_chest"));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.RARE, ContainerTrade.Builder.create()
                .setPaymentStack(new ItemStack(ModItems.OPAL, 64))
                .setOfferStack(chest)
                .setContainerItem(new ItemStack(ModItems.OPAL_SWORD), 0)
                .setContainerItem(tip, 1)
                .setMaxTrades(1)
                .setMerchantExperience(100)
                .build());
    }

    public void registerEpicEndGoblinTrades() {
        /* ************************************************************************************** *
         *                                      EPIC                                              *
         * ************************************************************************************** */
        ItemStack specialDiamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
        Text nothing = Text.literal("");
        Text symbol = Text.literal("♦").formatted(Formatting.GOLD);
        Text hoe = Items.DIAMOND_HOE.getName().copy().formatted(Formatting.LIGHT_PURPLE);
        Text axe = Items.DIAMOND_AXE.getName().copy().formatted(Formatting.LIGHT_PURPLE);
        Text pickaxe = Items.DIAMOND_PICKAXE.getName().copy().formatted(Formatting.LIGHT_PURPLE);
        specialDiamondPickaxe.setCustomName(nothing.copy().append(symbol).append(" ").append(pickaxe).append(" ").append(symbol));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.EPIC, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 24))
                .setOfferStack(specialDiamondPickaxe)
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                
                .setExperience(20)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.FORTUNE, 5))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.MENDING, 1))
                .build());

        ItemStack specialDiamondAxe = new ItemStack(Items.DIAMOND_AXE);
        specialDiamondAxe.setCustomName(nothing.copy().append(symbol).append(" ").append(axe).append(" ").append(symbol));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.EPIC, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND_AXE))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 24))
                .setOfferStack(specialDiamondAxe)
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                
                .setExperience(20)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, 5))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.MENDING, 1))
                .build());

        ItemStack specialDiamondHoe = new ItemStack(Items.DIAMOND_HOE);
        specialDiamondHoe.setCustomName(nothing.copy().append(symbol).append(" ").append(hoe).append(" ").append(symbol));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.EPIC, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND_HOE))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 24))
                .setOfferStack(specialDiamondAxe)
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                
                .setExperience(20)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.MENDING, 1))
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.EPIC, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.TOTEM_OF_UNDYING))
                .setSecondaryPaymentStack(new ItemStack(ModItems.OPAL, 15))
                .setOfferStack(new ItemStack(ModItems.DURABILITY_TOTEM))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setExperience(900)
                .build());
    }

    public void registerLegendaryEndGoblinTrades() {
        /* ************************************************************************************** *
         *                                    LEGENDARY                                           *
         * ************************************************************************************** */
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.LEGENDARY, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DRAGON_HEAD, 25))
                .setSecondaryPaymentStack(new ItemStack(Items.DIAMOND_SWORD, 1))
                .setOfferStack(new ItemStack(Items.NETHERITE_SWORD))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setExperience(900)
                .addEnchantment(new EnchantmentLevelEntry(ModEnchantments.OPAL_INFUSED, 1))
                .build());
    }
}
