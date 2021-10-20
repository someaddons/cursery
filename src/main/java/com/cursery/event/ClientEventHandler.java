package com.cursery.event;

import com.cursery.Cursery;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class ClientEventHandler
{
    @SubscribeEvent
    public static void on(ItemTooltipEvent event)
    {
        if (!Cursery.config.getCommonConfig().showDesc.get())
        {
            return;
        }

        if (event.getItemStack().getItem() instanceof EnchantedBookItem)
        {
            for (final Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.getEnchantments(event.getItemStack()).entrySet())
            {
                if (entry.getKey().isCurse())
                {
                    event.getToolTip().add(new TranslatableComponent("enchanted_book_cursed.desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
                    return;
                }

                if (!(entry.getKey().isTreasureOnly() && Cursery.config.getCommonConfig().excludeTreasure.get()))
                {
                    event.getToolTip().add(new TranslatableComponent("enchanted_book.desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
                    return;
                }
            }
        }
        else if (event.getItemStack().getItem() instanceof BlockItem && ((BlockItem) event.getItemStack().getItem()).getBlock() instanceof EnchantmentTableBlock)
        {
            event.getToolTip().add(new TranslatableComponent("enchanted_table.desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
        }
    }
}
