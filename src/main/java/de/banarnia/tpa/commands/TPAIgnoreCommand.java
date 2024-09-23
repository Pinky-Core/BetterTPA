package de.banarnia.tpa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import de.banarnia.api.acf.bukkit.contexts.OnlinePlayer;
import de.banarnia.tpa.manager.IgnoreManager;
import org.bukkit.entity.Player;

@CommandAlias("tpaignore")
public class TPAIgnoreCommand extends BaseCommand {

    private IgnoreManager manager;

    public TPAIgnoreCommand(IgnoreManager manager) {
        this.manager = manager;
    }

    @Default
    @CommandCompletion("@players")
    public void ignore(Player sender, OnlinePlayer target) {
        manager.toggleIgnore(sender, target.getPlayer(), false);
    }

}
