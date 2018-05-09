import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nirmoho-Mac
 *
 */
public class PaacApi {
    private static final PaacApi INSTANCE = new PaacApi();
    
    public static PaacApi getInstance() {
      return INSTANCE;
    }
    
    public String callAPI(String endPoint, Map<String, String> params) throws MalformedURLException  {
        String json = "";
        URL url = new URL(Constants.Api.BASE_URL + endPoint);
        String query = url.getQuery();
        if (query == null)  {
            url = new URL(Constants.Api.BASE_URL + endPoint + "?key=" + Constants.Api.API_KEY);
        }
        System.out.println(Constants.Api.BASE_URL + endPoint + "?key=" + Constants.Api.API_KEY);
        return json;
    }
    public String getRoutes() throws IOException   {
        Map<String, String> params = new HashMap<>();
        String endPoint = "/getroutes";
        String json = this.callAPI(endPoint, params);
        System.out.println(json);
        return json;
    }

}