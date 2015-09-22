package org.jcsamples;

import org.jcsamples.model.Route;

import java.util.List;
import java.util.Random;

/**
 * Created by neurons on 9/21/15.
 */
public class RwUtils {
    private static Random randomGenerator = new Random();
    public static Route getRandomRoute(List<Route> items){
        int index = randomGenerator.nextInt(items.size());
        return items.get(index);
    }
}
