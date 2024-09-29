package de.banarnia.bettertpa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import de.banarnia.bettertpa.manager.TPAManager;
import org.bukkit.entity.Player;

@CommandAlias("tpdeny")
public class TPDenyCommand extends BaseCommand {

    private TPAManager manager;
    private TPAcceptCommand acceptCommand;

    public TPDenyCommand(TPAManager manager, TPAcceptCommand acceptCommand) {
        this.manager = manager;
        this.acceptCommand = acceptCommand;
    }

    @Default
    @CommandCompletion("@players")
    public void deny(Player sender, @Optional OnlinePlayer target) {
        acceptCommand.respond(sender, false, target);
    }

}
