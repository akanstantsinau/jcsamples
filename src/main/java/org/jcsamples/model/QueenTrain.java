package org.jcsamples.model;

/**
 * Created by neurons on 9/22/15.
 */
public class QueenTrain extends BaseTrain implements Comparable{
    private boolean queen = false;

    public QueenTrain(Long direction) {
        super(direction);
    }

    public boolean isQueen() {
        return queen;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }

    @Override
    public void go(Railway railway) {
        throw new UnsupportedOperationException();
    }

    public void go(Route route){
        this.route = route;
        goOnRoute(route);
    }

    public int compareTo(Object o) {
        if(queen && !((QueenTrain)o).queen){
            return -1;
        }else if(((QueenTrain)o).queen && !queen){
            return 1;
        }
        return 0;
    }
}
