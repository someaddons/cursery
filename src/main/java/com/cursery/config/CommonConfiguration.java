package com.cursery.config;

import com.cursery.Cursery;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class CommonConfiguration
{
    public List<String> excludedCurses  = new ArrayList<>();
    public boolean      excludeTreasure = false;
    public boolean      debugTries      = false;
    public boolean      showDesc        = true;
    public boolean      visualSuccess   = true;
    public int          basecursechance = 5;

    protected CommonConfiguration()
    {
        excludedCurses.add("minecraft:vanishing_curse");
    }

    public JsonObject serialize()
    {
        final JsonObject root = new JsonObject();

        final JsonObject entry = new JsonObject();
        entry.addProperty("desc:", "Should enchanted books show a hint for curse magic, default:true");
        entry.addProperty("showDesc", showDesc);
        root.add("showDesc", entry);

        final JsonObject entry1 = new JsonObject();
        entry1.addProperty("desc:", "Add a curse id here to exclude it from beeing applied. "
                                      + "To put multiple values seperate them by commas like this:  [\"minecraft:curse\", \"mod:curse;\"] ");
        final JsonArray list1 = new JsonArray();
        for (final String name : excludedCurses)
        {
            list1.add(name);
        }
        entry1.add("excludedCurses", list1);
        root.add("excludedCurses", entry1);

        final JsonObject entry2 = new JsonObject();
        entry2.addProperty("desc:", "Should applying treasure enchants be excluded, default:false");
        entry2.addProperty("excludeTreasure", excludeTreasure);
        root.add("excludeTreasure", entry2);

        final JsonObject entry3 = new JsonObject();
        entry3.addProperty("desc:", "Base curse application chance, scales up the more enchants the item has. Default:5 %");
        entry3.addProperty("basecursechance", basecursechance);
        root.add("basecursechance", entry3);

        final JsonObject entry4 = new JsonObject();
        entry4.addProperty("desc:", "Whether to log debug messages about curse chances beeing rolled, default = false");
        entry4.addProperty("debugTries", debugTries);
        root.add("debugTries", entry4);

        final JsonObject entry5 = new JsonObject();
        entry5.addProperty("desc:", "Should enchanting success play a sound and show particles, default:true");
        entry5.addProperty("visualSuccess", visualSuccess);
        root.add("visualSuccess", entry5);

        return root;
    }

    public void deserialize(JsonObject data)
    {
        if (data == null)
        {
            Cursery.LOGGER.error("Config file was empty!");
            return;
        }

        try
        {
            showDesc = data.get("showDesc").getAsJsonObject().get("showDesc").getAsBoolean();
            excludeTreasure = data.get("excludeTreasure").getAsJsonObject().get("excludeTreasure").getAsBoolean();
            debugTries = data.get("debugTries").getAsJsonObject().get("debugTries").getAsBoolean();
            visualSuccess = data.get("visualSuccess").getAsJsonObject().get("visualSuccess").getAsBoolean();
            basecursechance = data.get("basecursechance").getAsJsonObject().get("basecursechance").getAsInt();
            excludedCurses = new ArrayList<>();
            for (final JsonElement element : data.get("excludedCurses").getAsJsonObject().get("excludedCurses").getAsJsonArray())
            {
                excludedCurses.add(element.getAsString());
            }
        }
        catch (Exception e)
        {
            Cursery.LOGGER.error("Could not parse config file", e);
        }
    }
}
