package de.banarnia.tpa.events;

import de.banarnia.api.events.BanarniaEvent;
import org.bukkit.event.Cancellable;

public abstract class TpRequestEvent extends BanarniaEvent implements Cancellable {

    protected boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
