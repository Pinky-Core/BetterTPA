package de.banarnia.bettertpa.requests;

import de.banarnia.bettertpa.events.requests.TpRequestEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public abstract class TPRequest {

    protected Player sender;
    protected Player receiver;
    protected boolean accepted;
    protected boolean denied;

    protected int expirationTaskId;
    protected int warmupTaskId;

    protected final String WARMUP_BYPASS_PERM = "bettertpa.warmup.bypass";
    protected final String COOLDOWN_BYPASS_PERM = "bettertpa.cooldown.bypass";

    public TPRequest(Player sender, Player receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Create a TPRequestEvent.
     * @return TPRequestEvent instance.
     */
    public abstract TpRequestEvent createRequestEvent();

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
        this.accepted = true;

        // Play sound.
        Location newLocation = sender.getLocation();
        newLocation.getWorld().playSound(newLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

        // Cancel expiration task.
        cancelExpirationTask();

        return true;
    }

    /**
     * Mark request as denied.
     */
    public void deny() {
        this.denied = true;

        // Cancel tasks.
        cancelExpirationTask();
        cancelWarmupTask();
    }

    /**
     * Cancel the Expiration task.
     */
    public void cancelExpirationTask() {
        if (expirationTaskId != 0)
            Bukkit.getScheduler().cancelTask(this.expirationTaskId);
    }

    /**
     * Cancel the teleport warmup task.
     */
    public void cancelWarmupTask() {
        if (warmupTaskId != 0)
            Bukkit.getScheduler().cancelTask(this.warmupTaskId);
    }

    /**
     * Check if request was accepted or denied.
     * @return True, if request was accepted or denied. Else false.
     */
    public boolean wasExecuted() {
        return accepted || denied;
    }

    /**
     * Execute the teleportation.
     */
    protected abstract void teleport();

    /**
     * Check if the player can bypass the teleport warmup.
     * @return True, if player to teleport can bypass the teleport warmup. Else false.
     */
    public boolean hasWarmupBypassPermission() {
        return getPlayerToTeleport().hasPermission(WARMUP_BYPASS_PERM);
    }

    /**
     * Check if the sender can bypass the request cooldown.
     * @return True, if sender can bypass the request cooldown. Else false.
     */
    public boolean hasCooldownBypassPermission() {
        return sender.hasPermission(COOLDOWN_BYPASS_PERM);
    }

    /**
     * Get the player that gets teleported in this request.
     * @return Player that will get teleported in this request type.
     */
    public abstract Player getPlayerToTeleport();

    /**
     * Check if warmup phase is active.
     * @return True, if teleport warmup is running. Else false.
     */
    public boolean hasWarmup() {
        return warmupTaskId != 0;
    }

    // Getter & Setter

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isDenied() {
        return denied;
    }

    public int getExpirationTaskId() {
        return expirationTaskId;
    }

    public void setExpirationTaskId(int expirationTaskId) {
        this.expirationTaskId = expirationTaskId;
    }

    public int getWarmupTaskId() {
        return warmupTaskId;
    }

    public void setWarmupTaskId(int warmupTaskId) {
        this.warmupTaskId = warmupTaskId;
        this.accepted = true;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }
}
