import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
    
    public String appendQueryParams(String initString, Map<String, String> params)  {
        StringBuilder sb = new StringBuilder(initString);
        sb.append("?key=" + Constants.Api.API_KEY);
        for (String key : params.keySet())  {
            sb.append("&" + key + "=" + params.get(key).replaceAll(" ", "%20"));
        }
        sb.append("&format=json");
        sb.append("&localestring=en_US");
        return sb.toString();
    }
    
    public String callAPI(String endPoint, Map<String, String> params) throws IOException  {
        String urlStr = Constants.Api.BASE_URL + endPoint;
        urlStr = appendQueryParams(urlStr, params);
        System.out.println(urlStr);
        URL url = new URL(urlStr);
        URLConnection urlConn = url.openConnection();
        InputStream in = urlConn.getInputStream();
        BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));

        StringBuilder sb = new StringBuilder();
        String respLine;
        while ((respLine = res.readLine()) != null)   {
            sb.append(respLine);
        }
        res.close();
        return sb.toString();
    }
    
    public String getRoutes() throws IOException   {
        Map<String, String> params = new HashMap<>();
        String endPoint = "/getroutes";
        String json = this.callAPI(endPoint, params);
        return json;
    }
    
    public String getStops() throws IOException {
        Map<String, String> params = new HashMap<>();
        String dir = "INBOUND";
        String route = "71D";
        String rtpidatafeed = "Port Authority Bus";
        params.put("rt", route);
        params.put("dir", dir);
        params.put("rtpidatafeed", rtpidatafeed);
        String endPoint = "/getstops";
        String json = this.callAPI(endPoint, params);
        return json;
        
    }
    
    

}