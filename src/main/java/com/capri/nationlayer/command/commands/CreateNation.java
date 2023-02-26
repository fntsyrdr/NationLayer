package com.capri.nationlayer.command.commands;

import com.capri.nationlayer.command.SubCommand;
import org.bukkit.entity.Player;

public class CreateNation extends SubCommand {
    @Override
    public String getName() {
        return "CreateNation";
    }

    @Override
    public String getDescription() {
        return "Creates a nation";
    }

    @Override
    public String getSyntax() {
        return "/NationLayer CreateNation, /nlcn";
    }

    @Override
    public void perform(Player player, String[] args) {

    }
}
