package dev.fh.animamorphacandy.listeners;

import dev.fh.animamorphacandy.MorphPlugin;
import dev.fh.animamorphacandy.config.MorphEntry;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

/**
 * Copyright 2017 (c) FalseHonesty
 * @author FalseHonesty
 */

public class EventListener implements Listener {
    private MorphPlugin plugin;

    public EventListener(MorphPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onFoodConsume(PlayerItemConsumeEvent e) {
        if (e.isCancelled()) return;

        for (MorphEntry entry : plugin.getConfigLoader().getMorphEntries()) {
            if (entry.getFoodType() != e.getItem().getType()) {
                continue;
            }

            Player player = e.getPlayer();
            player.addPotionEffects(entry.getPotionEffects());

            entry.disguise(player);

            break;
        }
    }
}
