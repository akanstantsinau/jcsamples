package org.jcsamples.model;

/**
 * Created by neurons on 9/22/15.
 */
public class TrainWithWaitNotify2 extends BaseTrain {
    public TrainWithWaitNotify2(Long direction){
        super(direction);
    }

    public void go(Railway railway){
        while(!completed) {
            for (Route tryRoute : railway.getRoutes()) {
                if (tryRoute.getLock().tryLock()) {
                    route = tryRoute;
                    goOnRoute(tryRoute);
                    tryRoute.getLock().unlock();

                    railway.getLock().lock();
                    railway.getRouteAwailable().signal();
                    railway.getLock().unlock();
                    break;
                }
            }
            try {
                railway.getLock().lock();
                railway.getRouteAwailable().await();
                railway.getLock().unlock();

            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
