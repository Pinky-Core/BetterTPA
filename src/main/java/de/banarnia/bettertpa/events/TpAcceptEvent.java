package de.banarnia.bettertpa.events;

import de.banarnia.api.events.BanarniaEvent;
import de.banarnia.bettertpa.requests.TPRequest;
import org.bukkit.event.Cancellable;

public class TpAcceptEvent extends BanarniaEvent implements Cancellable {

    private TPRequest request;
    private boolean cancelled;

    public TpAcceptEvent(TPRequest request) {
        this.request = request;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public TPRequest getRequest() {
        return request;
    }
}
