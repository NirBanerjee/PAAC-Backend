import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import Domains.Stops;
import Domains.Routes;
import Domains.Direction;

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
        List<Routes> routeList = api.getRoutes();
        Routes route = new Routes ("71D", "HAMILTON", "#cd5c5c");
        List<Direction> dirList = api.getDirections(route); 
        List<Stops> stopList = api.getStops(route, dirList.get(0));
    }

}
