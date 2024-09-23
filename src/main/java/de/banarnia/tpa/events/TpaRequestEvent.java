package de.banarnia.tpa.events;

import de.banarnia.tpa.requests.TpaRequest;

public class TpaRequestEvent extends TpRequestEvent {

    private final TpaRequest request;

    public TpaRequestEvent(TpaRequest request) {
        this.request = request;
    }

    public TpaRequest getRequest() {
        return request;
    }
}
