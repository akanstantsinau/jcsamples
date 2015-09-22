package org.jcsamples.model;

import org.jcsamples.RwUtils;

/**
 * A high-speed Eurostar train
 */
public abstract class BaseTrain {
    public final static Long LONDON_TO_PARIS_DIRECTION=1L;
    public final static Long PARIS_TO_LONDON_DIRECTION=-1L;
    public final static Long ONE_HOUR_OF_JOURNEY=100L;
    public final static Long LONDON_TO_PARIS_DISTANCE=800L;
    public final static Long TRAIN_SPEED=10L;

    protected volatile Long position;
    protected Long direction;
    protected final Long speed = TRAIN_SPEED;
    protected volatile Route route;
    protected volatile Boolean completed = false;

    public BaseTrain(Long direction){
        this.direction = direction;
        if(direction==LONDON_TO_PARIS_DIRECTION){
            position=0L;
        }else if(direction==PARIS_TO_LONDON_DIRECTION){
            position=LONDON_TO_PARIS_DISTANCE;
        }
    }

    abstract public void go(Railway railway);

    protected void goOnRoute(Route route){
        while(tripInProggress()) {
            try {
                Thread.sleep(ONE_HOUR_OF_JOURNEY);
            } catch (InterruptedException e) {
                break;
            }
            position += speed * direction;
        }
        completed = true;
    }

    public boolean tripInProggress(){
        if(LONDON_TO_PARIS_DIRECTION==direction){
            return position < LONDON_TO_PARIS_DISTANCE;
        }else{
            return position > 0;
        }
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Long getDirection() {
        return direction;
    }

    public Long getPosition() {
        return position;
    }

    public Route getRoute() {
        return route;
    }
}
