package org.jcsamples.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A railway configuration between London and Paris.
 */
public class Railway {

    private Lock lock = new ReentrantLock();
    private Condition routeAwailable = lock.newCondition();

    private List<Route> routes = new ArrayList<Route>();

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Condition getRouteAwailable() {
        return routeAwailable;
    }

    public Lock getLock() {
        return lock;
    }
}
