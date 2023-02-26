package com.capri.nationlayer.command;

import com.capri.nationlayer.command.commands.AcceptInvite;
import com.capri.nationlayer.command.commands.CreateNation;
import com.capri.nationlayer.command.commands.DeleteGroup;
import com.capri.nationlayer.command.commands.LeaveGroup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class CommandHandler implements CommandExecutor {
    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandHandler(){
        subCommands.add(new CreateNation());
        subCommands.add(new DeleteGroup());
        subCommands.add(new AcceptInvite());
        subCommands.add(new LeaveGroup());
    }


    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        return false;
    }
}
