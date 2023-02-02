package com.cursery.config;

import com.cursery.Cursery;
import com.cursery.enchant.CurseEnchantmentHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.cursery.enchant.CurseEnchantmentHelper.curseWeightMap;
import static com.cursery.enchant.CurseEnchantmentHelper.totalCurseWeight;

public class Configuration
{
    /**
     * Loaded everywhere, not synced
     */
    private final CommonConfiguration commonConfig = new CommonConfiguration();

    /**
     * Loaded clientside, not synced
     */
    // private final ClientConfiguration clientConfig;
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Builds configuration tree.
     */
    public Configuration()
    {
    }

    public void load()
    {
        final Path configPath = FabricLoader.getInstance().getConfigDir().normalize().resolve(Cursery.MODID + ".json");
        final File config = configPath.toFile();

        if (!config.exists())
        {
            Cursery.LOGGER.warn("Config for limited chunks not found, recreating default");
            save();
        }
        else
        {
            try
            {
                commonConfig.deserialize(gson.fromJson(Files.newBufferedReader(configPath), JsonObject.class));
            }
            catch (IOException e)
            {
                Cursery.LOGGER.error("Could not read config from:" + configPath, e);
            }
        }

        parseConfig();
    }

    public void save()
    {
        final Path configPath = FabricLoader.getInstance().getConfigDir().normalize().resolve(Cursery.MODID + ".json");
        try
        {
            final BufferedWriter writer = Files.newBufferedWriter(configPath);
            gson.toJson(commonConfig.serialize(), JsonObject.class, writer);
            writer.close();
        }
        catch (IOException e)
        {
            Cursery.LOGGER.error("Could not write config to:" + configPath, e);
        }
    }

    public CommonConfiguration getCommonConfig()
    {
        return commonConfig;
    }

    public void parseConfig()
    {
        curseWeightMap = new HashMap<>();
        totalCurseWeight = 0;
        final Set<Enchantment> excluded = new HashSet<>();
        for (final String entry : Cursery.config.getCommonConfig().excludedCurses)
        {
            final ResourceLocation id = ResourceLocation.tryParse(entry);
            if (id == null)
            {
                Cursery.LOGGER.error("Config entry could not be parsed, not a valid resource location " + entry);
                continue;
            }

            final Enchantment enchantment = BuiltInRegistries.ENCHANTMENT.get(id);
            if (enchantment == null)
            {
                Cursery.LOGGER.error("Config entry could not be parsed, not a valid enchant" + entry);
                continue;
            }

            excluded.add(enchantment);
        }

        // Parse registry entries
        for (final Map.Entry<ResourceKey<Enchantment>, Enchantment> enchantmentEntry : BuiltInRegistries.ENCHANTMENT.entrySet())
        {
            if (enchantmentEntry.getValue().isCurse())
            {
                if (!excluded.contains(enchantmentEntry.getValue()))
                {
                    final int weight = CurseEnchantmentHelper.calculateWeightFor(enchantmentEntry.getValue());
                    totalCurseWeight += weight;
                    CurseEnchantmentHelper.curseWeightMap.put(enchantmentEntry.getValue(), weight);
                }
                else
                {
                    Cursery.LOGGER.info("Excluding curse: " + BuiltInRegistries.ENCHANTMENT.getKey(enchantmentEntry.getValue()) + " as config disables it");
                }
            }
        }
    }
}
