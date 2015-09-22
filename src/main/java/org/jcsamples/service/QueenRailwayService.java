package org.jcsamples.service;

import org.jcsamples.model.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by neurons on 9/21/15.
 */
@Component
public class QueenRailwayService {
    private PriorityBlockingQueue<QueenTrain> trainQueue = new PriorityBlockingQueue<QueenTrain>();
    private List<QueenTrain> trains = new LinkedList<QueenTrain>();

    private Railway railway;

    public QueenRailwayService(){
        railway = new Railway();
        railway.getRoutes().add(new Route("routeA"));
        railway.getRoutes().add(new Route("routeB"));
        startTrainConsumers();
    }

    public Collection<QueenTrain> getTrains(){
        return trains;
    }

    public void newTrainToLondon(boolean queen){
        QueenTrain train = new QueenTrain(Train.PARIS_TO_LONDON_DIRECTION);
        train.setQueen(queen);
        trainQueue.add(train);
        trains.add(train);
    }

    public void newTrainToParis(boolean queen){
        QueenTrain train = new QueenTrain(Train.LONDON_TO_PARIS_DIRECTION);
        train.setQueen(queen);
        trainQueue.add(train);
        trains.add(train);
    }

    private void startTrainConsumers(){

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        QueenTrain train = trainQueue.take();
                        train.go(railway.getRoutes().get(0));
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        QueenTrain train = trainQueue.take();
                        train.go(railway.getRoutes().get(1));
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }.start();
    }
}
