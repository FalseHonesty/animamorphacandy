package dev.fh.animamorphacandy.config;

import dev.fh.animamorphacandy.MorphPlugin;
import lombok.Getter;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2017 (c) FalseHonesty
 * @author FalseHonesty
 */

public class MorphEntry {
    @Getter
    private final Material foodType;
    @Getter
    private final ArrayList<PotionEffect> potionEffects;
    @Getter
    private final DisguiseType disguiseType;
    @Getter
    private final int disguiseTime;

    public MorphEntry(Material foodType, ArrayList<PotionEffect> potionEffects, DisguiseType disguiseType, int disguiseTime) {
        this.foodType = foodType;
        this.potionEffects = potionEffects;
        this.disguiseType = disguiseType;
        this.disguiseTime = disguiseTime;
    }

    public static MorphEntry parseMorphEntry(String foodType, ArrayList<List> potionEffects, List disguiseType) {
        Material materialFoodType = Material.valueOf(foodType);
        ArrayList<PotionEffect> listPotionEffects = new ArrayList<>();

        for (List potionEffect : potionEffects) {
            PotionEffectType effectType = PotionEffectType.getByName((String) potionEffect.get(0));
            int duration = (int) potionEffect.get(1);
            int amplifier = (int) potionEffect.get(2);

            listPotionEffects.add(new PotionEffect(effectType, duration * 20, amplifier));
        }

        DisguiseType disguiseDisguiseType = null;
        int disguiseTime = 0;
        String disguiseName = (String) disguiseType.get(0);

        if (disguiseName != null) {
            disguiseDisguiseType = DisguiseType.valueOf(disguiseName.toUpperCase());
            disguiseTime = (int) disguiseType.get(1);
        }

        return new MorphEntry(materialFoodType, listPotionEffects, disguiseDisguiseType, disguiseTime);
    }

    public void disguise(Entity entity) {
        if (disguiseType != null && entity != null) {
            MobDisguise disguise = new MobDisguise(disguiseType);

            DisguiseAPI.disguiseToAll(entity, disguise);

            Bukkit.getScheduler().runTaskLater(MorphPlugin.plugin, () -> {
                undisguise(entity);
            }, 20 * disguiseTime);
        }
    }

    public void undisguise(Entity entity) {
        if (entity != null) {
            DisguiseAPI.undisguiseToAll(entity);
        }
    }

    @Override
    public String toString() {
        return "{type:" + foodType.toString() + ",effects:" + potionEffects.toString() + ",disguise:" + disguiseType + "}";
    }
}
