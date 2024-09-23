package de.banarnia.tpa.manager;

import de.banarnia.tpa.BetterTPA;
import de.banarnia.tpa.config.TPAConfig;
import de.banarnia.tpa.events.TpRequestEvent;
import de.banarnia.tpa.events.TpaRequestEvent;
import de.banarnia.tpa.lang.Message;
import de.banarnia.tpa.requests.TPRequest;
import de.banarnia.tpa.requests.TpaRequest;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TPAManager {

    private BetterTPA plugin;
    private TPAConfig config;

    private IgnoreManager ignoreManager;

    private HashMap<UUID, HashMap<UUID, TPRequest>> pendingRequests = new HashMap<>();

    public TPAManager(BetterTPA plugin, TPAConfig config) {
        this.plugin = plugin;
        this.config = config;
        this.ignoreManager = new IgnoreManager();
    }

    // ~~~~~ TPA ~~~~~

    public void requestTpa(Player sender, Player receiver, boolean silent) {
        // Check if there is already a pending request.
        if (checkPendingRequest(sender, receiver, silent))
            return;

        // Check if there is a cooldown.
        if (checkCooldown(sender, receiver, silent))
            return;

        // Check if player is ignored.
        if (checkIgnore(sender, receiver, silent))
            return;

        // Create request and throw event.
        TpaRequest request = new TpaRequest(sender, receiver);
        TpRequestEvent event = new TpaRequestEvent(request);
        event.callEvent();

        // Check if event was cancelled.
        if (event.isCancelled()) {
            if (!silent) {
                String message = Message.COMMAND_ERROR_TPA_EVENT_CANCELLED.get();
                sender.sendMessage(message);
            }

            return;
        }

        // Add to pending events.
        addRequest(sender, receiver, request);

        // Send message.
        if (!silent) {
            sender.sendMessage(Message.COMMAND_INFO_TPA_SENDER.replace("%target%", receiver.getName()));
            // TODO: Send accept message to receiver.
        }
    }

    /**
     * Check if the sender already has a pending request to that player.
     * @param sender Request sender.
     * @param receiver Request receiver.
     * @param silent Set to true to send message.
     * @return True, if receiver has a pending request by sender. Else false.
     */
    public boolean checkPendingRequest(Player sender, Player receiver, boolean silent) {
        if (!hasPendingRequest(receiver, sender))
            return false;

        if (!silent) {
            String message = Message.COMMAND_ERROR_TPA_PENDING.get();
            sender.sendMessage(message);
        }

        return true;
    }

    /**
     * Check if the sender has a cooldown on submitting a new tpa request to a player.
     * @param sender Sender that initiates the request.
     * @param receiver Request receiver.
     * @param silent Set to true to send message.
     * @return True, if cooldown is still active.
     */
    public boolean checkCooldown(Player sender, Player receiver, boolean silent) {
        if (!hasCooldown(sender, receiver))
            return false;

        if (!silent) {
            String message = Message.COMMAND_ERROR_TPA_COOLDOWN.get();
            sender.sendMessage(message);
        }

        return true;
    }

    /**
     * Check if the sender is ignored by the receiver.
     * @param sender Sender that might be ignored.
     * @param receiver Receiver that might have ignored the sender.
     * @param silent Set to true to send message.
     * @return True, if player is ignored. Else false.
     */
    public boolean checkIgnore(Player sender, Player receiver, boolean silent) {
        if (!ignoreManager.isIgnored(sender, receiver) || ignoreManager.hasIgnoreBypassPermission(sender))
            return false;

        if (!silent) {
            String message = Message.COMMAND_ERROR_TPA_IGNORED.replace("%target%", receiver.getName());
            sender.sendMessage(message);
        }

        return true;
    }

    // ~~~~~ Requests ~~~~~

    /**
     * Add a new pending request.
     * @param sender Request sender.
     * @param receiver Request receiver.
     * @param request Request instance.
     */
    public void addRequest(Player sender, Player receiver, TPRequest request) {
        // Check if receiver already has a pending request.
        if (hasPendingRequest(receiver, sender))
            return;

        if (!hasPendingRequest(receiver))
            pendingRequests.put(receiver.getUniqueId(), new HashMap<>());

        pendingRequests.get(receiver.getUniqueId()).put(sender.getUniqueId(), request);
    }

    /**
     * Check if a player has a pending request of another player.
     * @param receiver Request receiver.
     * @param sender Request sender.
     * @return True, if receiver has a pending request of the sender. Else false.
     */
    public boolean hasPendingRequest(Player receiver, Player sender) {
        // Check if receiver has any pending requests at all.
        if (!hasPendingRequest(receiver))
            return false;

        return pendingRequests.get(receiver.getUniqueId()).containsKey(sender.getUniqueId());
    }

    /**
     * Check if a player has pending requests.
     * @param receiver Receiver to check.
     * @return True, if player has pending request. Else false.
     */
    public boolean hasPendingRequest(Player receiver) {
        return pendingRequests.containsKey(receiver.getUniqueId());
    }

    // ~~~~~ Cooldown ~~~~~

    public boolean hasCooldown(Player sender, Player receiver) {
        // TODO: Implement
        return true;
    }

}
