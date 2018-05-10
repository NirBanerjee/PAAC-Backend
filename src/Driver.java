import java.util.ArrayList;
import java.util.List;

import Domains.Direction;
import Domains.Prediction;
import Domains.Routes;
import Domains.Stops;

/**
 * 
 */

/**
 * @author nirmoho-Mac
 *
 */
public class Driver {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        PaacApi api = new PaacApi();
//        List<Routes> routeList = api.getRoutes();
        Routes route = new Routes ("71D", "HAMILTON", "#cd5c5c");
        List<Direction> dirList = api.getDirections(route); 
        List<Stops> stopList = api.getStops(route, dirList.get(0));
//        List<Stops> smallList =  new ArrayList<>();
//        for (int i = 0; i < 10; i++)    {
//            smallList.add(stopList.get(i));
//        }
//        List<Prediction> prdList = api.getPredictions(smallList);
//        
//        for (Prediction p : prdList)    {
//            System.out.println(p);
//        }
        api.getTransit(stopList.get(0), stopList.get(12));
            
    }

}
