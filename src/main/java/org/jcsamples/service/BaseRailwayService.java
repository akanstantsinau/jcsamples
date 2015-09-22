package org.jcsamples.service;

import org.jcsamples.model.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Simple eurostar train service
 *
 */
@Component
public class BaseRailwayService {
    private List<BaseTrain> trains = new LinkedList<BaseTrain>();

    private Railway railway;

    public BaseRailwayService(){
        railway = new Railway();
        railway.getRoutes().add(new Route("routeA"));
        railway.getRoutes().add(new Route("routeB"));
    }

    public List<BaseTrain> getTrains(){
        return trains;
    }

    public void newTrainToLondon(){
        final Train train = new Train(Train.PARIS_TO_LONDON_DIRECTION);
        //final BaseTrain train = new TrainWithLock(Train.PARIS_TO_LONDON_DIRECTION);
        //final BaseTrain train = new TrainWithWaitNotify(Train.PARIS_TO_LONDON_DIRECTION);
        //final BaseTrain train = new TrainWithWaitNotify2(Train.PARIS_TO_LONDON_DIRECTION);
        processTrain(train);
    }

    public void newTrainToParis(){
        final Train train = new Train(Train.LONDON_TO_PARIS_DIRECTION);
        //final BaseTrain train = new TrainWithLock(Train.LONDON_TO_PARIS_DIRECTION);
        //final BaseTrain train = new TrainWithWaitNotify(Train.LONDON_TO_PARIS_DIRECTION);
        //final BaseTrain train = new TrainWithWaitNotify2(Train.LONDON_TO_PARIS_DIRECTION);
        processTrain(train);
    }

    private void processTrain(final BaseTrain train){
        trains.add(train);
        new Thread(){
            @Override
            public void run() {
                train.go(railway);
            }
        }.start();
    }
}
