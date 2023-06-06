package org.fasttrackit.exception;

import lombok.Getter;

@Getter
public class ResourcesNotFound extends RuntimeException{

    private final long entityId;

    public ResourcesNotFound(String message, long entityId) {
        super(message);
        this.entityId = entityId;
    }
}
