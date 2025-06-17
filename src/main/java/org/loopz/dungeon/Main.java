package org.loopz.dungeon;

import org.bukkit.plugin.java.JavaPlugin;
import org.loopz.dungeon.utils.Language;

import java.io.File;

public class Main extends JavaPlugin {

    public final Language language = new Language();

    public void onEnable() {
        language.init(this);
    }

}