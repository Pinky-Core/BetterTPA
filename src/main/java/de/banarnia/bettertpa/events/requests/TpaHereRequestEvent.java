package de.banarnia.bettertpa.events.requests;

import de.banarnia.bettertpa.requests.TpaHereRequest;

public class TpaHereRequestEvent extends TpRequestEvent {

    private final TpaHereRequest request;

    public TpaHereRequestEvent(TpaHereRequest request) {
        this.request = request;
    }

    public TpaHereRequest getRequest() {
        return request;
    }

}
