package com.capri.nationlayer.command.commands;

import com.capri.nationlayer.command.SubCommand;
import com.capri.nationlayer.config.ConfigManager;
import com.capri.nationlayer.db.Database;
import org.bukkit.entity.Player;

public class DeleteGroup extends SubCommand {
    @Override
    public String getName() {
        return "DeleteGroup";
    }

    @Override
    public String getDescription() {
        return "Deletes Your NationLayer Group";
    }

    @Override
    public String getSyntax() {
        return "/NationLayer DeleteGroup";
    }

    @Override
    public void perform(Player player, String[] args) {


    }
}
