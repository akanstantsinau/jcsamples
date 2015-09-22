package org.jcsamples.model;

/**
 * Created by neurons on 9/22/15.
 */
public class TrainWithWaitNotify extends BaseTrain {
    public TrainWithWaitNotify(Long direction){
        super(direction);
    }

    public void go(Railway railway){
        while(!completed) {
            for (Route tryRoute : railway.getRoutes()) {
                if (tryRoute.getLock().tryLock()) {
                    route = tryRoute;
                    goOnRoute(tryRoute);
                    tryRoute.getLock().unlock();
                    synchronized (railway) {
                        railway.notify();
                    }
                    break;
                }
            }
            try {
                synchronized (railway) {
                    railway.wait();
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
