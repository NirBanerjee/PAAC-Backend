import java.io.IOException;

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
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        PaacApi api = new PaacApi();
        System.out.println(api.getRoutes());
        
    }

}
