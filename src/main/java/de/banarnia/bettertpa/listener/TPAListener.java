package de.banarnia.bettertpa.listener;

import de.banarnia.bettertpa.manager.TPAManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class TPAListener implements Listener {

    private TPAManager manager;

    public TPAListener(TPAManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void handleQuit(PlayerQuitEvent event) {
        manager.handlePlayerQuit(event.getPlayer());
    }

}
