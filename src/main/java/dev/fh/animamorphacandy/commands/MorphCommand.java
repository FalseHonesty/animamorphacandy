package dev.fh.animamorphacandy.commands;

import dev.fh.animamorphacandy.MorphPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Copyright 2017 (c) FalseHonesty
 *
 * @author FalseHonesty
 */

public class MorphCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("reload")) {
            commandSender.sendMessage(ChatColor.GREEN + "Reloading Animamorphacandy plugin...");
            MorphPlugin.plugin.getConfigLoader().loadConfig();
            commandSender.sendMessage(ChatColor.GREEN + "Done!");
        }

        return true;
    }
}
