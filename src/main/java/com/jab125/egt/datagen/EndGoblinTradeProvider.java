package com.jab125.egt.datagen;

import com.jab125.egt.entities.EndGoblinTraderEntity;
import com.jab125.egt.init.ModEnchantments;
import com.jab125.egt.init.ModEntities;
import com.jab125.egt.init.ModItems;
import com.jab125.util.datagen.TradeProvider;
import com.jab125.util.tradehelper.TradeRarity;
import com.jab125.util.tradehelper.type.UpgradedBasicTrade;
import net.hat.gt.init.ModPotions;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class EndGoblinTradeProvider extends TradeProvider {
    protected EndGoblinTradeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    public void registerTrades()
    {
        System.out.println("TRADES REGISTERED");
        this.registerEndGoblinTraderTrades();
    }

    @Override
    protected void registerVanillaTrades() {
        registerCommonEndGoblinTrades(true);
        registerUncommonEndGoblinTrades(true);
        registerRareEndGoblinTrades(true);
        registerEpicEndGoblinTrades(true);
        registerLegendaryEndGoblinTrades(true);
    }

    public void registerEndGoblinTraderTrades() {
        registerCommonEndGoblinTrades(false);
        registerUncommonEndGoblinTrades(false);
        registerRareEndGoblinTrades(false);
        registerEpicEndGoblinTrades(false);
        registerLegendaryEndGoblinTrades(false);
    }


    public ItemStack itemFromPotion(Potion potion) {
        ItemStack potionStack = new ItemStack(Items.POTION);
        PotionUtil.setPotion(potionStack, potion);
        return potionStack;
    }
    
    public void registerCommonEndGoblinTrades(boolean isVanilla) {
        /* ************************************************************************************** *
         *                                     COMMON                                             *
         * ************************************************************************************** */
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND, 17))
                .setOfferStack(itemFromPotion(isVanilla ? ModPotions.HASTE : ModPotions.HASTE_EXTENDED))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(50)
                .setMerchantExperience(10)
                .setPlayerExperience(96)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ENDER_PEARL, 10))
                .setOfferStack(itemFromPotion(Potions.SLOW_FALLING))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(10)
                .setMerchantExperience(2)
                .setPlayerExperience(26)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 1))
                .setOfferStack(itemFromPotion(ModPotions.WEAK_NIGHT_VISION))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(ModItems.OPAL, 1))
                .setOfferStack(itemFromPotion(ModPotions.BLINDNESS_EXTENDED_2))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(12)
                .setMerchantExperience(1)
                .setPlayerExperience(13)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.END_STONE, 12))
                .setOfferStack(new ItemStack(Items.AZALEA))
                .setPriceMultiplier(0F)
                .setMaxTrades(128)
                .setMerchantExperience(2)
                .setPlayerExperience(2)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ENDER_PEARL, 16))
                .setOfferStack(new ItemStack(Items.PHANTOM_MEMBRANE))
                .setPriceMultiplier(0F)
                .setMaxTrades(164)
                .setMerchantExperience(2)
                .setPlayerExperience(2)
                .build());
    }
    public void registerUncommonEndGoblinTrades(boolean isVanilla) {
        /* ************************************************************************************** *
         *                                     UNCOMMON                                           *
         * ************************************************************************************** */
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.UNCOMMON, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(itemFromPotion(isVanilla ? Potions.STRONG_HEALING : ModPotions.POWERFUL_INSTANT_HEALTH))
                .setOfferStack(new ItemStack(ModItems.OPAL))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());
    }

    public void registerRareEndGoblinTrades(boolean isVanilla) {
        /* ************************************************************************************** *
         *                                      RARE                                              *
         * ************************************************************************************** */

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.RARE, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 3))
                .setOfferStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setMerchantExperience(4)
                .setPlayerExperience(40)
                .build());

        if (!isVanilla) {
            ItemStack knockbackStick = new ItemStack(Items.STICK);
            knockbackStick.setCustomName(new TranslatableText(Enchantments.KNOCKBACK.getTranslationKey()).append(" ").append(new TranslatableText(Items.STICK.getTranslationKey())));
            this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.RARE, isVanilla, UpgradedBasicTrade.Builder.create()
                    .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 3))
                    .setOfferStack(knockbackStick)
                    .setPriceMultiplier(0F)
                    .setMaxTrades(64)
                    .setMerchantExperience(4)
                    .setPlayerExperience(40)
                    .addEnchantment(new EnchantmentLevelEntry(Enchantments.KNOCKBACK, 2))
                    .build());
        }
    }

    public void registerEpicEndGoblinTrades(boolean isVanilla) {
        /* ************************************************************************************** *
         *                                      EPIC                                              *
         * ************************************************************************************** */
        ItemStack specialDiamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
        Text nothing = new LiteralText("");
        Text symbol = new LiteralText("♦").formatted(Formatting.GOLD);
        Text axe = Items.DIAMOND_AXE.getName().copy().formatted(Formatting.LIGHT_PURPLE);
        Text pickaxe = Items.DIAMOND_PICKAXE.getName().copy().formatted(Formatting.LIGHT_PURPLE);
        specialDiamondPickaxe.setCustomName(nothing.copy().append(symbol).append(" ").append(pickaxe).append(" ").append(symbol));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.EPIC, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHERITE_INGOT, isVanilla ? 12 : 24))
                .setOfferStack(specialDiamondPickaxe)
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, isVanilla ? 5 : 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, isVanilla ? 3 : 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.FORTUNE, isVanilla ? 3 : 5))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.MENDING, 1))
                .build());

        ItemStack specialDiamondAxe = new ItemStack(Items.DIAMOND_AXE);
        specialDiamondAxe.setCustomName(nothing.copy().append(symbol).append(" ").append(axe).append(" ").append(symbol));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.EPIC, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND_AXE))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHERITE_INGOT, isVanilla ? 12 : 24))
                .setOfferStack(specialDiamondAxe)
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, isVanilla ? 5 : 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, isVanilla ? 3 : 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, isVanilla ? 3 : 5))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.MENDING, 1))
                .build());
    }

    public void registerLegendaryEndGoblinTrades(boolean isVanilla) {
        /* ************************************************************************************** *
         *                                    LEGENDARY                                           *
         * ************************************************************************************** */
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.LEGENDARY, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DRAGON_HEAD, 25))
                .setSecondaryPaymentStack(new ItemStack(Items.DIAMOND_SWORD, 1))
                .setOfferStack(new ItemStack(Items.NETHERITE_SWORD))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(9)
                .setPlayerExperience(900)
                .addEnchantment(new EnchantmentLevelEntry(ModEnchantments.OPAL_INFUSED, 1))
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.LEGENDARY, isVanilla, UpgradedBasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.TOTEM_OF_UNDYING))
                .setSecondaryPaymentStack(new ItemStack(ModItems.OPAL, 15))
                .setOfferStack(new ItemStack(ModItems.DURABILITY_TOTEM))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(9)
                .setPlayerExperience(900)
                .addEnchantment(new EnchantmentLevelEntry(ModEnchantments.OPAL_INFUSED, 1))
                .build());
    }
}
