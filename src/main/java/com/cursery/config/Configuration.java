package com.cursery.config;

import com.cursery.Cursery;
import com.cursery.enchant.CurseEnchantmentHelper;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

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
    private final CommonConfiguration commonConfig;

    /**
     * Loaded clientside, not synced
     */
    // private final ClientConfiguration clientConfig;

    /**
     * Builds configuration tree.
     */
    public Configuration()
    {
        commonConfig = new CommonConfiguration(new ForgeConfigSpec.Builder());
        loadConfig(commonConfig.ForgeConfigSpecBuilder, FMLPaths.CONFIGDIR.get().resolve(Cursery.MODID + "-common.toml"));
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path)
    {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                                                 .sync()
                                                 .preserveInsertionOrder()
                                                 .autosave()
                                                 .writingMode(WritingMode.REPLACE)
                                                 .build();
        configData.load();
        spec.setConfig(configData);
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
        for (final String entry : Cursery.config.getCommonConfig().excludedCUrses.get())
        {
            final ResourceLocation id = ResourceLocation.tryParse(entry);
            if (id == null)
            {
                Cursery.LOGGER.error("Config entry could not be parsed, not a valid resource location " + entry);
                continue;
            }

            final Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(id);
            if (enchantment == null)
            {
                Cursery.LOGGER.error("Config entry could not be parsed, not a valid enchant" + entry);
                continue;
            }

            excluded.add(enchantment);
        }

        // Parse registry entries
        for (final Map.Entry<RegistryKey<Enchantment>, Enchantment> enchantmentEntry : ForgeRegistries.ENCHANTMENTS.getEntries())
        {
            if (enchantmentEntry.getValue().isCurse())
            {
                if (!excluded.contains(enchantmentEntry.getValue()))
                {
                    final int weight = CurseEnchantmentHelper.calculateWeightFor(enchantmentEntry.getValue());
                    totalCurseWeight += weight;
                    CurseEnchantmentHelper.curseWeightMap.put(enchantmentEntry.getValue(), weight);
                }
            }
        }
    }
}
