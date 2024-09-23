package de.banarnia.tpa.requests;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TpaHereRequest extends TPRequest {

    public TpaHereRequest(Player sender, Player receiver) {
        super(sender, receiver);
    }

    @Override
    protected void teleport() {
        // Teleport.
        receiver.teleport(sender.getLocation());

        // Play sound.
        sender.getLocation().getWorld().playSound(receiver.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }
}
