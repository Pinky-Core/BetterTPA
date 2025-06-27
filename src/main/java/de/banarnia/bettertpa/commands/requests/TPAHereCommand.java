package de.banarnia.bettertpa.commands.requests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import de.banarnia.bettertpa.manager.TPAManager;
import de.banarnia.bettertpa.requests.TpaHereRequest;
import org.bukkit.entity.Player;

@CommandAlias("tpahere")
@CommandPermission("bettertpa.tpahere")
public class TPAHereCommand extends BaseCommand {

    private TPAManager manager;

    public TPAHereCommand(TPAManager manager) {
        this.manager = manager;
    }

    @Default
    @CommandCompletion("@tpaPlayers")
    public void tpaHere(Player sender, OnlinePlayer target) {
        manager.sendRequest(new TpaHereRequest(sender, target.getPlayer()), false);
    }

}
