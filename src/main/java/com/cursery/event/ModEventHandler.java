package com.cursery.event;

import com.cursery.Cursery;
import net.minecraft.block.Blocks;
import net.minecraft.item.AirItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class ModEventHandler
{
    @SubscribeEvent
    public static void onConfigChanged(ModConfig.ModConfigEvent event)
    {
        Cursery.config.parseConfig();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        for (final String name : Cursery.config.getCommonConfig().disabledItems.get())
        {
            event.getRegistry().register(new AirItem(Blocks.AIR, new Item.Properties()).setRegistryName(name));
        }
    }
}
