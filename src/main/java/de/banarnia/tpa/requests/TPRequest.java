package de.banarnia.tpa.requests;

import org.bukkit.entity.Player;

public abstract class TPRequest {

    protected Player sender;
    protected Player receiver;

    public TPRequest(Player sender, Player receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Check if both players are still online.
     * @return True, if both players are online. Else false.
     */
    public boolean bothPlayersOnline() {
        return isSenderOnline() && isReceiverOnline();
    }

    /**
     * Check if the request sender is still online.
     * @return True, if the request sender is online. Else false.
     */
    public boolean isSenderOnline() {
        return sender != null && sender.isOnline();
    }

    /**
     * Check if the request receiver is still online.
     * @return True, if the request receiver is online. Else false.
     */
    public boolean isReceiverOnline() {
        return receiver != null && receiver.isOnline();
    }

    /**
     * Teleports the player, if both players are online.
     * @return True, if both players are online. Else false.
     */
    public boolean execute() {
        // Check if players are online.
        if (!bothPlayersOnline())
            return false;

        // Teleport.
        teleport();

        return true;
    }

    /**
     * Execute the teleportation.
     */
    protected abstract void teleport();

}
