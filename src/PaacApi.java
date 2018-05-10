import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Domains.Direction;
import Domains.Routes;
import Domains.Stops;

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
        //System.out.println(urlStr);
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
    
    public List<Routes> getRoutes() throws IOException, JSONException   {
        Map<String, String> params = new HashMap<>();
        List<Routes> routeList = new ArrayList<>();
        String endPoint = "/getroutes";
        String json = this.callAPI(endPoint, params);
        JSONObject obj = new JSONObject(json);
        JSONObject busTime = (JSONObject) obj.get("bustime-response");
        JSONArray routes = busTime.getJSONArray("routes");
        for (int i = 0; i < routes.length(); i++)    {
            JSONObject rtObject = routes.getJSONObject(i);
            Routes rt = new Routes(rtObject.getString("rt")
                    ,rtObject.getString("rtnm")
                    ,rtObject.getString("rtclr"));
            routeList.add(rt);
        }
        return routeList;
    }
    
    public List<Stops> getStops(Routes route, Direction direction) throws IOException, JSONException  {
        Map<String, String> params = new HashMap<>();
        List<Stops> stopList = new ArrayList<>();
        String dir = direction.getDirection();
        String rt = route.getRt();
        String rtpidatafeed = "Port Authority Bus";
        params.put("rt", rt);
        params.put("dir", dir);
        params.put("rtpidatafeed", rtpidatafeed);
        String endPoint = "/getstops";
        String json = this.callAPI(endPoint, params);
        JSONObject obj = new JSONObject(json);
        JSONObject busTime = (JSONObject) obj.get("bustime-response");
        JSONArray stops = busTime.getJSONArray("stops");
        for (int i = 0; i < stops.length(); i++)  {
            JSONObject stopObject = stops.getJSONObject(i);
            Stops stp = new Stops (stopObject.get("stpid").toString()
                    , stopObject.get("stpnm").toString()
                    ,stopObject.getDouble("lat")
                    ,stopObject.getDouble("lon"));
            
            stopList.add(stp);
        }
        return stopList;
    }
    
    public List<Direction> getDirections(Routes route) throws IOException, JSONException   {
        List<Direction> directionList = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        String rtpidatafeed = "Port Authority Bus";
        String rt = route.getRt();
        params.put("rtpidatafeed", rtpidatafeed);
        params.put("rt", rt);
        String endPoint = "/getdirections";
        String json = this.callAPI(endPoint, params);
        JSONObject obj = new JSONObject(json);
        JSONObject busTime = (JSONObject) obj.get("bustime-response");
        JSONArray directions = busTime.getJSONArray("directions");
        for (int i = 0; i < directions.length(); i++)  {
            JSONObject dirObject = directions.getJSONObject(i);
            Direction dir = new Direction (dirObject.get("name").toString());
            directionList.add(dir);
        }
        return directionList;
    }
    
    public List
    

}