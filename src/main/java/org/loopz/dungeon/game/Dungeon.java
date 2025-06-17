package org.loopz.dungeon.game;

import lombok.Data;
import org.bukkit.Location;

@Data
public class Dungeon {

    private int name;
    private int slots;
    private int waves;
    private Location spawn;
    private Location bossSpawn;

}
