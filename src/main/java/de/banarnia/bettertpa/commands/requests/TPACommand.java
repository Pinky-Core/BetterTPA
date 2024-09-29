package de.banarnia.bettertpa.commands.requests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import de.banarnia.bettertpa.lang.Message;
import de.banarnia.bettertpa.manager.TPAManager;
import de.banarnia.bettertpa.requests.TpaRequest;
import org.bukkit.entity.Player;

@CommandAlias("tpa")
public class TPACommand extends BaseCommand {

    private TPAManager manager;

    public TPACommand(TPAManager manager) {
        this.manager = manager;
    }

    @Default
    @CommandCompletion("@tpaPlayers")
    public void tpa(Player sender, OnlinePlayer target) {
        manager.sendRequest(new TpaRequest(sender, target.getPlayer()), false);
    }

    @Subcommand("reload")
    @CommandPermission("bettertpa.reload")
    public void reload(CommandIssuer sender) {
        manager.getConfig().reload();
        sender.sendMessage(Message.COMMAND_INFO_RELOAD.get());
    }

}
