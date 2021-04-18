package com.hostilevillages.event;

import com.hostilevillages.RandomVillageDataSet;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class ModEventHandler
{
    @SubscribeEvent
    public static void onConfigChanged(ModConfig.ModConfigEvent event)
    {
        RandomVillageDataSet.parseFromConfig();
    }
}
