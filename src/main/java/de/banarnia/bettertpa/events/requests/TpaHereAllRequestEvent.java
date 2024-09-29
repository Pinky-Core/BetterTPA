package de.banarnia.bettertpa.events.requests;

import de.banarnia.bettertpa.requests.TpaHereAllRequest;

public class TpaHereAllRequestEvent extends TpRequestEvent {

    private final TpaHereAllRequest request;

    public TpaHereAllRequestEvent(TpaHereAllRequest request) {
        this.request = request;
    }

    public TpaHereAllRequest getRequest() {
        return request;
    }
}
