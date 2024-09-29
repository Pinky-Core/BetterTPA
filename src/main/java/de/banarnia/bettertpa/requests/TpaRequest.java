package de.banarnia.bettertpa.requests;

import de.banarnia.bettertpa.events.requests.TpRequestEvent;
import de.banarnia.bettertpa.events.requests.TpaRequestEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TpaRequest extends TPRequest {

    public TpaRequest(Player sender, Player receiver) {
        super(sender, receiver);
    }

    @Override
    public TpRequestEvent createRequestEvent() {
        return new TpaRequestEvent(this);
    }

    @Override
    public void teleport() {
        // Teleport.
        sender.teleport(receiver.getLocation());

        // Play sound.
        receiver.getLocation().getWorld().playSound(receiver.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }

    @Override
    public Player getPlayerToTeleport() {
        return sender;
    }
}
