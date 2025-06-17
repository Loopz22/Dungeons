package org.loopz.dungeon.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.loopz.dungeon.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Language {
    private final Map<String, FileConfiguration> langs = new HashMap<>();
    private String defaultLang = "pt_BR";
    private Main plugin;

    public void init(Main plugin) {
        this.plugin = plugin;
        defaultLang = plugin.getConfig().getString("defaultLang");
        File langsFolder = new File(plugin.getDataFolder(), "lang");
        langsFolder.mkdirs();
        loadLangFile(langsFolder, "pt_BR");
        loadLangFile(langsFolder, "en_US");
    }

    private void loadLangFile(File dataFolder, String lang) {
        File file = new File(dataFolder, "lang/" + lang + ".yml");
        if (!file.exists()) return;
        langs.put(lang, YamlConfiguration.loadConfiguration(file));
    }

    private String get(String key, Map<String, String> placeholders) {
        String lang = defaultLang;
        FileConfiguration config = langs.getOrDefault(lang, langs.get(defaultLang));

        String raw = config.getString(key, "§c[LANG ERROR] " + key);
        String prefix = langs.get(defaultLang).getString("prefix", "");

        String message = raw.replace("%prefix%", prefix);

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("%" + entry.getKey() + "%", entry.getValue());
        }

        return ChatColor.translateAlternateColorCodes('&', message);

    }

    private List<String> getList(Player player, String key, Map<String, String> placeholders) {
        String lang = defaultLang;
        FileConfiguration config = langs.getOrDefault(lang, langs.get(defaultLang));

        List<String> lines = config.getStringList(key);
        if (lines.isEmpty()) {
            lines.add("§c[LANG LIST ERROR] " + key);
        }

        String prefix = langs.get(defaultLang).getString("prefix", "");

        List<String> result = new ArrayList<>();
        for (String line : lines) {
            String formatted = line.replace("%prefix%", prefix);
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                formatted = formatted.replace("%" + entry.getKey() + "%", entry.getValue());
            }
            result.add(ChatColor.translateAlternateColorCodes('&', formatted));
        }

        return result;
    }

    public String get(Player player, String key) {
        Map<String, String> map = new HashMap<>();
        map.put("player", player != null ? player.getName() : "%player%");
        map.put("wave", waveNumber != null ? waveNumber : "%wave%");
        return get(key, map);
    }

    public List<String> getList(Player player, String key) {
        Map<String, String> map = new HashMap<>();
        map.put("player", player != null ? player.getName() : "%player%");
        map.put("wave", waveNumber != null ? waveNumber : "%wave%");
        return getList(player, key, map);
    }



}
