package de.banarnia.bettertpa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import de.banarnia.bettertpa.lang.Message;
import de.banarnia.bettertpa.manager.TPAManager;
import de.banarnia.bettertpa.requests.TPRequest;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("tpaccept")
public class TPAcceptCommand extends BaseCommand {

    private TPAManager manager;
    private TPAListCommand listCommand;

    public TPAcceptCommand(TPAManager manager, TPAListCommand listCommand) {
        this.manager = manager;
        this.listCommand = listCommand;
    }

    @Default
    @CommandCompletion("@players")
    public void accept(Player sender, @Optional OnlinePlayer target) {
        respond(sender, true, target);
    }

    /**
     * Accept or deny a tpa request.
     * @param sender Command sender.
     * @param accept True = accept, False = deny.
     * @param target Target player.
     */
    public void respond(Player sender, boolean accept, @Optional OnlinePlayer target) {
        // Check if target was specified.
        if (target == null) {
            List<TPRequest> requests = manager.getPendingRequests(sender);

            // Check if player has any pending requests.
            if (requests.isEmpty()) {
                sender.sendMessage(Message.COMMAND_ERROR_TPACCEPT_EMPTY.get());
                return;
            }

            // List pending requests, if player has multiple pending requests.
            if (requests.size() > 1) {
                listCommand.list(sender);
                return;
            }

            // Accept or deny the only existing request.
            TPRequest request = requests.get(0);
            if (accept)
                manager.acceptRequest(request, false);
            else
                manager.denyRequest(request, false);

            return;
        }

        // Check if sender has a pending request of the target.
        TPRequest request = manager.getPendingRequest(sender, target.getPlayer());
        if (request == null) {
            sender.sendMessage(Message.COMMAND_ERROR_TPACCEPT_INVALID.replace("%target%", target.getPlayer().getName()));
            return;
        }

        // Accept or deny the only existing request.
        if (accept)
            manager.acceptRequest(request, false);
        else
            manager.denyRequest(request, false);
    }

}
