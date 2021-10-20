package com.jab125.egt.init;

import com.google.common.collect.ImmutableMap;
import com.jab125.egt.trades.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.hat.gt.init.ModPotions;
import net.hat.gt.trades.SellEnchantedItem;
import net.hat.gt.trades.TradeWithoutEmerald;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.gen.feature.StructureFeature;

import static net.hat.gt.init.ModTrades.copyToFastUtilMap;


public class ModTrades {
    public static Int2ObjectMap<TradeOffers.Factory[]> END_GOBLIN_TRADER_TRADES;
    private static final ItemStack BROKEN_ELYTRA = new ItemStack(Items.ELYTRA);

    static {
        BROKEN_ELYTRA.setDamage(BROKEN_ELYTRA.getMaxDamage());
        END_GOBLIN_TRADER_TRADES = copyToFastUtilMap(ImmutableMap.of(
                /**
                 * Right so if you're reading through the code and are planning on making an addon or something to add custom trades -
                 * at least before I do... this is where the trades are done. They are easily accessable here, and you can put your own
                 * in via a mixin into this. I've made my own ItemFactory called TradeWithoutEmerald, this is due to default
                 * tradeFactories all using emeralds, so I needed my own.
                 *
                 * The default format is:PaymentItem, Price, SellItem, SellCount, MaxUses, ExperienceGained
                 *
                 * There is also a second format if you wish to add a second Item -
                 * The format is: PaymentItem, Price, SecondItem, SecondPrice, SellItem, SellCount, MaxUses, ExperienceGained
                 *
                 * I've also went ahead and made everything public in here, so feel free to access it without an accesswidener :)
                 */
                /* ************************************************************************************** *
                 *                                     COMMON                                             *
                 * ************************************************************************************** */
                1, new TradeOffers.Factory[]{
                        new TradeWithoutEmeraldPotionsNoAwkward(Items.DIAMOND, 17, ModPotions.HASTE_EXTENDED, 50, 96),
                        new TradeWithoutEmeraldPotionsNoAwkward(Items.ENDER_PEARL, 10, Potions.SLOW_FALLING, 10, 26),
                        new TradeWithoutEmeraldPotionsNoAwkward(Items.EMERALD, 1, ModPotions.WEAK_NIGHT_VISION, 15, 26),
                        new TradeWithoutEmerald(Items.END_STONE, 12, Items.AZALEA, 1, 2),
                        new TradeWithoutEmerald(Items.ENDER_PEARL, 16, Items.PHANTOM_MEMBRANE, 1, 64, 2),
                        new TradeWithoutEmerald(Items.ACACIA_BOAT, 1, Items.ACACIA_PLANKS, 5, 999, 0),
                        new TradeWithoutEmerald(Items.EMERALD, 1, Items.ACACIA_PLANKS, 5, 999, 0),

                        /* ************************************************************************************** *
                         *                                     UNCOMMON                                           *
                         * ************************************************************************************** */
                }, 2, new TradeOffers.Factory[]{
                        new TradeWithoutEmeraldSellMapTo00Factory(Items.DIAMOND, 15, MapIcon.Type.RED_X, 1, 15),
                        new TradeWithoutEmeraldItemStack(PotionUtil.setPotion(new ItemStack(Items.POTION), ModPotions.POWERFUL_INSTANT_HEALTH), 1, new ItemStack(Items.EMERALD), 15, 32, 20),
                        /* ************************************************************************************** *
                         *                                      RARE                                              *
                         * ************************************************************************************** */
                }, 3, new TradeOffers.Factory[]{
                        new TradeWithoutEmerald(Items.NETHERITE_INGOT, 3, Items.DIAMOND_PICKAXE, 1, 64, 40),
                        new TradeWithoutEmerald(Items.PURPUR_BLOCK, 64, Items.PURPUR_BLOCK, 64, Items.NETHERITE_INGOT, 1, 1, 1),

                        /* ************************************************************************************** *
                         *                                    LEGENDARY                                           *
                         * ************************************************************************************** */

                }, 4, new TradeOffers.Factory[]{
                        // new TradeWithoutEmeraldItemStack(new ItemStack(Items.NETHERITE_INGOT), 64, BROKEN_ELYTRA, 1, 0, 112),
                        new TradeWithoutEmeraldSellMapFactory(Items.EMERALD, 32, StructureFeature.END_CITY, MapIcon.Type.RED_X, 1, 11),
                        new OnlyInEndTradeWithoutEmerald(Items.NETHERITE_INGOT, 15, ModItems.TELEPORT_TO_SPAWN_SPELL, 1, 1, 26),
                        // If it spawns in the overworld
                        new TradeWithoutEmeraldSellMapFactory(Items.GOLD_INGOT, 48, Items.ENDER_EYE, 11, StructureFeature.STRONGHOLD, MapIcon.Type.RED_X, 1, 11),
                }
        ));
    }
}
