package de.banarnia.bettertpa.events.requests;

import de.banarnia.bettertpa.requests.TpaRequest;

public class TpaRequestEvent extends TpRequestEvent {

    private final TpaRequest request;

    public TpaRequestEvent(TpaRequest request) {
        this.request = request;
    }

    public TpaRequest getRequest() {
        return request;
    }
}
