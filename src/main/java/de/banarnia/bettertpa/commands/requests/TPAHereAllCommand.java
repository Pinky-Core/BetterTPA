package de.banarnia.bettertpa.commands.requests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import de.banarnia.bettertpa.manager.TPAManager;
import de.banarnia.bettertpa.requests.TpaHereAllRequest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("tpahereall")
@CommandPermission("bettertpa.tpahereall")
public class TPAHereAllCommand extends BaseCommand {

    private TPAManager manager;

    public TPAHereAllCommand(TPAManager manager) {
        this.manager = manager;
    }

    @Default
    public void tpaHereAll(Player sender) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != sender)
                manager.sendRequest(new TpaHereAllRequest(sender, player), false);
        }
    }

}
