package com.capri.nationlayer.model.faction;

import com.capri.nationlayer.model.struct.Roles;
import com.capri.nationlayer.model.struct.Relation;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Nation {
    private String name;
    private String password;
    private UUID owner;
    private List<UUID> allies;
    private List<UUID> war;
    private boolean isDisciplined;
    private boolean isValid = true;
    private UUID id;
    private Map<UUID, NationPlayer> members;
    private List<String> invites;
    private long activityTimestamp;


    private Nation superGroup;
    private boolean isSuperGroup;



    public Nation(String name, UUID owner, boolean disciplined, String password, Boolean isSuperGroup){
        this.name = name;
        this.password = password;
        this.owner = owner;
        this.isDisciplined = disciplined;
        this.id = UUID.randomUUID();
        this.isSuperGroup = isSuperGroup;
        this.members = new HashMap<>();
        this.allies = new ArrayList<>();
    }

    public NationPlayer getOwner(){
        return this.members.values().stream().filter(member -> member.getRole() == Roles.PRIMARY_OWNER).findFirst().orElse(null);
    }

    public Relation getRelation(Nation other) {
        Nation nation = this;

        return nation == other ? Relation.NEUTRAL : nation.isAlly(other) ? Relation.ALLY : Relation.HOSTILE;
    }

    public UUID getId() {
        return id;
    }
    public NationPlayer getMember(OfflinePlayer player){
        return player.hasPlayedBefore() || player.isOnline() ? this.getMember(player.getUniqueId()) : null;
    }
    public NationPlayer getMember(String name){
        return this.getMember(Bukkit.getOfflinePlayer(name));
    }
    public NationPlayer getMember(Player player){
        return this.getMember(player.getUniqueId());
    }
    public NationPlayer getMember(UUID uuid){
        return this.members.get(uuid);
    }

    public void addMember(NationPlayer nationPlayer){
        this.members.put(nationPlayer.getNationId(), nationPlayer);
    }
    public void removeMember(NationPlayer nationPlayer){
        this.members.remove(nationPlayer);
    }
    public boolean isAlly(Nation nation){
        if(nation == null) return false;
        return this.allies.contains(nation.getId());
    }
    public void addAlly(Nation nation){
        this.allies.add(nation.getId());
    }
    public List<Nation> getAlliesAsNations(){
        return this.allies.stream().map(NationManager.getInstance()::getNationByUUID).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }
}
