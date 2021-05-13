package com.cursery.event;

import com.cursery.Cursery;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.BlockItem;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class ClientEventHandler
{
    @SubscribeEvent
    public static void on(ItemTooltipEvent event)
    {
        if (event.getItemStack().getItem() instanceof EnchantedBookItem)
        {
            for (final Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.getEnchantments(event.getItemStack()).entrySet())
            {
                if (entry.getKey().isCurse())
                {
                    event.getToolTip().add(new TranslationTextComponent("enchanted_book_cursed.desc").setStyle(Style.EMPTY.withColor(TextFormatting.DARK_PURPLE)));
                    return;
                }

                if (!(entry.getKey().isTreasureOnly() && Cursery.config.getCommonConfig().excludeTreasure.get()))
                {
                    event.getToolTip().add(new TranslationTextComponent("enchanted_book.desc").setStyle(Style.EMPTY.withColor(TextFormatting.DARK_PURPLE)));
                    return;
                }
            }
        }
        else if (event.getItemStack().getItem() instanceof BlockItem && ((BlockItem) event.getItemStack().getItem()).getBlock() instanceof EnchantingTableBlock)
        {
            event.getToolTip().add(new TranslationTextComponent("enchanted_table.desc").setStyle(Style.EMPTY.withColor(TextFormatting.DARK_PURPLE)));
        }
    }
}
