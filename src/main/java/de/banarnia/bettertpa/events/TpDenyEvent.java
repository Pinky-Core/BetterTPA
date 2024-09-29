package de.banarnia.bettertpa.events;

import de.banarnia.api.events.BanarniaEvent;
import de.banarnia.bettertpa.requests.TPRequest;

public class TpDenyEvent extends BanarniaEvent {

    private TPRequest request;

    public TpDenyEvent(TPRequest request) {
        this.request = request;
    }

    public TPRequest getRequest() {
        return request;
    }
}
