package com.cursery.event;

import com.cursery.Cursery;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class ModEventHandler
{
    @SubscribeEvent
    public static void onConfigChanged(ModConfigEvent event)
    {
        Cursery.config.parseConfig();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerItems(RegisterEvent event)
    {
        if (event.getForgeRegistry() != null && event.getForgeRegistry().equals(ForgeRegistries.ITEMS))
        {
            for (final String name : Cursery.config.getCommonConfig().disabledItems.get())
            {
                ForgeRegistries.ITEMS.register(name, new AirItem(Blocks.AIR, new Item.Properties()));
            }
        }
    }
}
