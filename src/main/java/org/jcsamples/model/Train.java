package org.jcsamples.model;

import org.jcsamples.RwUtils;

/**
 * A high-speed Eurostar train
 */
public class Train extends BaseTrain{



    public Train(Long direction){
        super(direction);
    }

    public void go(Railway railway){
        route = RwUtils.getRandomRoute(railway.getRoutes());

        synchronized (route){
            goOnRoute(route);
        }
    }


}
