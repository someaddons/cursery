package com.cursery.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Collections;
import java.util.List;

public class CommonConfiguration
{
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> excludedCUrses;
    public final ForgeConfigSpec.ConfigValue<Boolean>                excludeTreasure;
    public final ForgeConfigSpec.ConfigValue<Integer>                basecursechance;
    public final ForgeConfigSpec                                     ForgeConfigSpecBuilder;

    protected CommonConfiguration(final ForgeConfigSpec.Builder builder)
    {
        builder.push("Cursery settings");
        //COnfig: affect players or all entities

        builder.comment(
          "Add a curse id here to exclude it from beeing applied. "
            + "To put multiple values seperate them by commas like this:  [\"minecraft:curse\", \"mod:curse;\"] ");
        excludedCUrses = builder.defineList("excludedCUrses",
          Collections.EMPTY_LIST
          , e -> e instanceof String && ((String) e).contains(":"));

        builder.comment("Should applying treasure enchants be excluded, default:false");
        excludeTreasure = builder.define("excludeTreasure", false);

        builder.comment("Base curse application chance, scales up the more enchants the item has. Default:15 %");
        basecursechance = builder.defineInRange("basecursechance", 15, 1, 100);

        // Escapes the current category level
        builder.pop();
        ForgeConfigSpecBuilder = builder.build();
    }
}
