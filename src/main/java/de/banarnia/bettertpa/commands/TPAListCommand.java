package de.banarnia.bettertpa.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import de.banarnia.bettertpa.lang.Message;
import de.banarnia.bettertpa.manager.TPAManager;
import de.banarnia.bettertpa.requests.TPRequest;
import de.banarnia.bettertpa.requests.TpaRequest;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@CommandAlias("tpalist")
public class TPAListCommand extends BaseCommand {

    private TPAManager manager;
    private BukkitAudiences adventure;
    private MiniMessage mm;

    public TPAListCommand(TPAManager manager, BukkitAudiences adventure, MiniMessage mm) {
        this.manager = manager;
        this.adventure = adventure;
        this.mm = mm;
    }

    @Default
    public void list(Player sender) {
        // Get all pending requests.
        List<TPRequest> requests = manager.getPendingRequests(sender).stream()
                .filter(request -> !request.wasExecuted() && !request.hasWarmup()).collect(Collectors.toList());

        // Check if player has any requests.
        if (requests.isEmpty()) {
            sender.sendMessage(Message.COMMAND_ERROR_TPACCEPT_EMPTY.get());
            return;
        }

        // Send messages.
        for (TPRequest request : manager.getPendingRequests(sender)) {
            sender.sendMessage(Message.COMMAND_INFO_LIST_HEADER.get());
            String msg = request instanceof TpaRequest ? Message.COMMAND_INFO_LIST_TPA.get() :
                                                         Message.COMMAND_INFO_LIST_TPAHERE.get();
            msg = msg.replace("%target%", request.getSender().getName());

            Component cmp = mm.deserialize(msg);
            adventure.player(sender).sendMessage(cmp);
        }
    }

}
