package org.loopz.dungeon.manager;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class User {

    private String name;
    private UUID uuid;
    private double coins;
    private double gold;
    private int mobKills;
    private int wins;

}
