package dev.fh.animamorphacandy.config;

import dev.fh.animamorphacandy.MorphPlugin;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2017 (c) FalseHonesty
 * @author FalseHonesty
 */

public class ConfigLoader {
    @Getter
    private ArrayList<MorphEntry> morphEntries;
    private MorphPlugin plugin;

    public ConfigLoader(MorphPlugin plugin) {
        this.plugin = plugin;
        this.loadConfig();
    }

    public ArrayList<MorphEntry> loadConfig() {
        plugin.saveResource("config.yml", false);
        FileConfiguration config = plugin.getConfig();

        ArrayList<MorphEntry> morphEntries = new ArrayList<>();

        for (String key : config.getKeys(false)) {
            ConfigurationSection foodConfig = config.getConfigurationSection(key);

            List effects = foodConfig.getList("effects");

            if (effects.size() % 3 != 0) {
                continue;
            }

            ArrayList<List> effectList = new ArrayList<>();

            for (int i = 0; i < effects.size();) {
                List subList = effects.subList(i, i += 3);

                effectList.add(subList);
            }

            MorphEntry morphEntry = MorphEntry.parseMorphEntry(key.toUpperCase(), effectList, foodConfig.getList("morph"));

            morphEntries.add(morphEntry);
        }

        this.morphEntries = morphEntries;
        return morphEntries;
    }
}
