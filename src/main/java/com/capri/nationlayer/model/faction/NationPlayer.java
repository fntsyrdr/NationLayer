package com.capri.nationlayer.model.faction;

import com.capri.nationlayer.model.struct.PermissionLevels;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NationPlayer {

    private UUID uuid;
    private UUID nationId;
    private PermissionLevels role;

    public NationPlayer(UUID uuid, Nation nation){
        this.uuid = uuid;
        this.nationId = nation.getId();
    }

    public UUID getNationId() {
        return nationId;
    }

    public Nation getNation(UUID uuid){
        return NationManager.getInstance().getNationByUUID(uuid);
    }
    public Player getPlayer(){
        return Bukkit.getPlayer(this.uuid);
    }

    public PermissionLevels getRole() {
        return role;
    }
}
