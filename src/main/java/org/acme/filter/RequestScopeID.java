package org.acme.filter;

import jakarta.enterprise.context.RequestScoped;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@RequestScoped
public class RequestScopeID implements Serializable {
    @Serial
    private static final long serialVersionUID = 5672822019923905164L;
    private final UUID requestID = UUID.randomUUID();

    public RequestScopeID() {
    }

    public String toString() {
        return this.requestID.toString();
    }
}

