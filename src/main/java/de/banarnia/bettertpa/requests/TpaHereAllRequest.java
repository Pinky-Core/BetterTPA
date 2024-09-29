package de.banarnia.bettertpa.requests;

import de.banarnia.bettertpa.events.requests.TpRequestEvent;
import de.banarnia.bettertpa.events.requests.TpaHereAllRequestEvent;
import org.bukkit.entity.Player;

public class TpaHereAllRequest extends TpaHereRequest {

    public TpaHereAllRequest(Player sender, Player receiver) {
        super(sender, receiver);
    }

    @Override
    public TpRequestEvent createRequestEvent() {
        return new TpaHereAllRequestEvent(this);
    }
}
