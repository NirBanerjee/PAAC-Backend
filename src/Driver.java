import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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
        System.out.println(api.getRoutes());
        List<Stops> result = api.getStops();
    }

}
