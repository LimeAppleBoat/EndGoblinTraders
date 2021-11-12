package com.jab125.egt.datagen;

import com.jab125.egt.entities.EndGoblinTraderEntity;
import com.jab125.egt.init.ModEnchantments;
import com.jab125.egt.init.ModEntities;
import com.jab125.egt.init.ModItems;
import com.jab125.util.datagen.TradeProvider;
import com.jab125.util.tradehelper.TradeRarity;
import com.jab125.util.tradehelper.type.BasicTrade;
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
    public void registerEndGoblinTraderTrades() {
        /* ************************************************************************************** *
         *                                     COMMON                                             *
         * ************************************************************************************** */
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND, 17))
                .setOfferStack(itemFromPotion(ModPotions.HASTE_EXTENDED))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(50)
                .setMerchantExperience(10)
                .setPlayerExperience(96)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ENDER_PEARL, 10))
                .setOfferStack(itemFromPotion(Potions.SLOW_FALLING))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(10)
                .setMerchantExperience(2)
                .setPlayerExperience(26)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.EMERALD, 1))
                .setOfferStack(itemFromPotion(ModPotions.WEAK_NIGHT_VISION))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(24)
                .setMerchantExperience(1)
                .setPlayerExperience(10)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(ModItems.OPAL, 1))
                .setOfferStack(itemFromPotion(ModPotions.BLINDNESS_EXTENDED_2))
                .setPriceMultiplier(0.05F)
                .setMaxTrades(12)
                .setMerchantExperience(1)
                .setPlayerExperience(13)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.END_STONE, 12))
                .setOfferStack(new ItemStack(Items.AZALEA))
                .setPriceMultiplier(0F)
                .setMaxTrades(128)
                .setMerchantExperience(2)
                .setPlayerExperience(2)
                .build());

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.COMMON, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.ENDER_PEARL, 16))
                .setOfferStack(new ItemStack(Items.PHANTOM_MEMBRANE))
                .setPriceMultiplier(0F)
                .setMaxTrades(164)
                .setMerchantExperience(2)
                .setPlayerExperience(2)
                .build());

        /* ************************************************************************************** *
         *                                     UNCOMMON                                           *
         * ************************************************************************************** */
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.UNCOMMON, BasicTrade.Builder.create()
                .setPaymentStack(itemFromPotion(ModPotions.POWERFUL_INSTANT_HEALTH))
                .setOfferStack(new ItemStack(ModItems.OPAL))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .build());

        /* ************************************************************************************** *
         *                                      RARE                                              *
         * ************************************************************************************** */

        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 3))
                .setOfferStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setMerchantExperience(4)
                .setPlayerExperience(40)
                .build());

        ItemStack knockbackStick = new ItemStack(Items.STICK);
        knockbackStick.setCustomName(new TranslatableText(Enchantments.KNOCKBACK.getTranslationKey()).append(" ").append(new TranslatableText(Items.STICK.getTranslationKey())));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.RARE, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 3))
                .setOfferStack(knockbackStick)
                .setPriceMultiplier(0F)
                .setMaxTrades(64)
                .setMerchantExperience(4)
                .setPlayerExperience(40)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.KNOCKBACK, 2))
                .build());

        /* ************************************************************************************** *
         *                                      EPIC                                              *
         * ************************************************************************************** */
        ItemStack specialDiamondPickaxe = new ItemStack(Items.DIAMOND_PICKAXE);
        Text nothing = new LiteralText("");
        Text symbol = new LiteralText("♦").formatted(Formatting.GOLD);
        Text axe = Items.DIAMOND_AXE.getName().copy().formatted(Formatting.LIGHT_PURPLE);
        Text pickaxe = Items.DIAMOND_PICKAXE.getName().copy().formatted(Formatting.LIGHT_PURPLE);
        specialDiamondPickaxe.setCustomName(nothing.copy().append(symbol).append(" ").append(pickaxe).append(" ").append(symbol));
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.EPIC, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DIAMOND_PICKAXE))
                .setSecondaryPaymentStack(new ItemStack(Items.NETHERITE_INGOT, 24))
                .setOfferStack(specialDiamondPickaxe)
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(2)
                .setPlayerExperience(20)
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
                .setMerchantExperience(2)
                .setPlayerExperience(20)
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.EFFICIENCY, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.UNBREAKING, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, 7))
                .addEnchantment(new EnchantmentLevelEntry(Enchantments.MENDING, 1))
                .build());

        /* ************************************************************************************** *
         *                                    LEGENDARY                                           *
         * ************************************************************************************** */
        this.addTrade(ModEntities.END_GOBLIN_TRADER, TradeRarity.LEGENDARY, BasicTrade.Builder.create()
                .setPaymentStack(new ItemStack(Items.DRAGON_HEAD, 25))
                .setSecondaryPaymentStack(new ItemStack(Items.DIAMOND_SWORD, 1))
                .setOfferStack(new ItemStack(Items.NETHERITE_SWORD))
                .setPriceMultiplier(0F)
                .setMaxTrades(32)
                .setMerchantExperience(9)
                .setPlayerExperience(900)
                .addEnchantment(new EnchantmentLevelEntry(ModEnchantments.OPAL_INFUSED, 1))
                .build());
    }


    public ItemStack itemFromPotion(Potion potion) {
        ItemStack potionStack = new ItemStack(Items.POTION);
        PotionUtil.setPotion(potionStack, potion);
        return potionStack;
    }
}
