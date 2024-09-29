package de.banarnia.bettertpa.requests;

import de.banarnia.bettertpa.events.requests.TpRequestEvent;
import de.banarnia.bettertpa.events.requests.TpaHereRequestEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TpaHereRequest extends TPRequest {

    public TpaHereRequest(Player sender, Player receiver) {
        super(sender, receiver);
    }

    @Override
    public TpRequestEvent createRequestEvent() {
        return new TpaHereRequestEvent(this);
    }

    @Override
    protected void teleport() {
        // Teleport.
        receiver.teleport(sender.getLocation());

        // Play sound.
        sender.getLocation().getWorld().playSound(receiver.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }

    @Override
    public Player getPlayerToTeleport() {
        return receiver;
    }
}
