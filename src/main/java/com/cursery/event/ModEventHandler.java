package com.cursery.event;

import com.cursery.Cursery;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;

public class ModEventHandler
{
    @SubscribeEvent
    public static void onConfigChanged(ModConfigEvent event)
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
