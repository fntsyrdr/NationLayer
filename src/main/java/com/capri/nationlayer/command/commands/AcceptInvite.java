package com.capri.nationlayer.command.commands;

import com.capri.nationlayer.command.SubCommand;
import org.bukkit.entity.Player;

public class AcceptInvite extends SubCommand {
    @Override
    public String getName() {
        return "AcceptInvite";
    }

    @Override
    public String getDescription() {
        return "Accepts an invite to join a Nation";
    }

    @Override
    public String getSyntax() {
        return "/NationLayer AcceptInvite, /nla";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 1){

        }

    }
}
