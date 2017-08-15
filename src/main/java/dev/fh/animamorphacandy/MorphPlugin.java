package dev.fh.animamorphacandy;

import dev.fh.animamorphacandy.config.ConfigLoader;
import dev.fh.animamorphacandy.listeners.EventListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright 2017 (c) FalseHonesty
 * @author FalseHonesty
 */

public class MorphPlugin extends JavaPlugin {

    public static MorphPlugin plugin;

    @Getter
    private ConfigLoader configLoader;
    @Getter
    private EventListener listener;

    @Override
    public void onEnable() {
        configLoader = new ConfigLoader(this);
        listener = new EventListener(this);

        plugin = this;
    }

    @Override
    public void onDisable() {

    }
}
