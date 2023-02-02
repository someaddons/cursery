package com.cursery.event;

import com.cursery.Cursery;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.EnchantmentTableBlock;

import java.util.List;
import java.util.Map;

public class ClientEventHandler
{
    public static void on(final ItemStack stack, List<Component> tooltips)
    {
        if (!Cursery.config.getCommonConfig().showDesc)
        {
            return;
        }

        if (stack.getItem() instanceof EnchantedBookItem)
        {
            for (final Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.getEnchantments(stack).entrySet())
            {
                if (entry.getKey().isCurse())
                {
                    tooltips.add(Component.translatable("enchanted_book_cursed.desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
                    return;
                }

                if (!(entry.getKey().isTreasureOnly() && Cursery.config.getCommonConfig().excludeTreasure))
                {
                    tooltips.add(Component.translatable("enchanted_book.desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
                    return;
                }
            }
        }
        else if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof EnchantmentTableBlock)
        {
            tooltips.add(Component.translatable("enchanted_table.desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
        }
    }
}
