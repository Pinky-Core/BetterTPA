package de.banarnia.tpa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import de.banarnia.tpa.manager.IgnoreManager;
import org.bukkit.entity.Player;

@CommandAlias("tpaignoreall")
public class TPAIgnoreAllCommand extends BaseCommand {

    private IgnoreManager manager;

    public TPAIgnoreAllCommand(IgnoreManager manager) {
        this.manager = manager;
    }

    @Default
    public void ignoreAll(Player sender) {
        manager.toggleIgnoreAllRequestsMode(sender, false);
    }

}
