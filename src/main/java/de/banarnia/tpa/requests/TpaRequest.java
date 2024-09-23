package de.banarnia.tpa.requests;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class TpaRequest extends TPRequest {

    public TpaRequest(Player sender, Player receiver) {
        super(sender, receiver);
    }

    @Override
    public void teleport() {
        // Teleport.
        sender.teleport(receiver.getLocation());

        // Play sound.
        receiver.getLocation().getWorld().playSound(receiver.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
    }
}
