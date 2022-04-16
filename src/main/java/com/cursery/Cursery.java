package com.cursery;

import com.cursery.config.Configuration;
import com.cursery.enchant.Enchants;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

// The value here should match an entry in the META-INF/mods.toml file
public class Cursery implements ModInitializer
{
    public static final String MODID = "cursery";

    public static final Random        rand   = new Random();
    public static final Logger        LOGGER = LogManager.getLogger();
    public static       Configuration config = new Configuration();

    public Cursery()
    {

    }

    @Override
    public void onInitialize()
    {
        Enchants.registerEnchants();
        config.load();
        LOGGER.info("Cursery initialized");
    }

    public static ResourceLocation curseryRL(final String path)
    {
        return new ResourceLocation(MODID, path);
    }
}
