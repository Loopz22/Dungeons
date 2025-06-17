package org.loopz.dungeon.game;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Boss {

    private String name;
    private int health;
    private String power;

}
