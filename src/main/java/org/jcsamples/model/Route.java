package org.jcsamples.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Single railway route
 */
@JsonIgnoreProperties({ "lock"})
public final class Route {
    private final String id;
    private Lock lock = new ReentrantLock(false);

    public Route(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Lock getLock(){
        return lock;
    }
}
