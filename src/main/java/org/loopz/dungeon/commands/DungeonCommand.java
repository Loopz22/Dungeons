package org.loopz.dungeon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.loopz.dungeon.Main;

public class DungeonCommand implements CommandExecutor {

    private Main plugin;

    public DungeonCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(plugin.language.get(null, "error-sender"));
            return false;
        }
        if(args.length == 0) {
            plugin.language.getList(null, "error-args")
        .forEach(sender::sendMessage);
        }
        return false;
    }
}
