package de.banarnia.tpa.commands;

import de.banarnia.api.acf.BaseCommand;
import de.banarnia.api.acf.annotation.CommandAlias;
import de.banarnia.api.acf.annotation.CommandCompletion;
import de.banarnia.api.acf.annotation.Default;
import de.banarnia.api.acf.bukkit.contexts.OnlinePlayer;
import de.banarnia.tpa.manager.TPAManager;
import org.bukkit.entity.Player;

@CommandAlias("tpa")
public class TPACommand extends BaseCommand {

    private TPAManager manager;

    public TPACommand(TPAManager manager) {
        this.manager = manager;
    }

    @Default
    @CommandCompletion("@players")
    public void tpa(Player sender, OnlinePlayer target) {

    }

}
