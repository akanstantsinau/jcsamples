package org.jcsamples.model;

import org.jcsamples.RwUtils;

/**
 * Created by neurons on 9/22/15.
 */
public class TrainWithLock extends BaseTrain {
    public TrainWithLock(Long direction){
        super(direction);
    }

    public void go(Railway railway){
        while(!completed) {
            for (Route tryRoute : railway.getRoutes()) {
                if (tryRoute.getLock().tryLock()) {
                    route = tryRoute;
                    goOnRoute(tryRoute);
                    tryRoute.getLock().unlock();
                    break;
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
