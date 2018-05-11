import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Domains.BusDetails;
import Domains.Direction;
import Domains.Prediction;
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
    
    public List<Prediction> getPredictions(List<Stops> stops) throws IOException, JSONException, ParseException{
        Map<String, String> params = new HashMap<>();
        List<Prediction> prdList = new ArrayList<>();
        String rtpidatafeed = "Port Authority Bus";
        List<String> stopIds = new ArrayList<>();
        for (Stops stop : stops) {
            stopIds.add(stop.getStpid());
        }
        params.put("stpid", String.join(",", stopIds));
        params.put("rtpidatafeed", rtpidatafeed);
        String endPoint = "/getpredictions";
        String json = this.callAPI(endPoint, params);
        JSONObject obj = new JSONObject(json);
        JSONObject busTime = (JSONObject) obj.get("bustime-response");
        JSONArray predictions = busTime.getJSONArray("prd");
        for (int i = 0; i < predictions.length(); i++)  {
            JSONObject prdObject = predictions.getJSONObject(i);
            String typ = prdObject.get("typ").toString();
            String stopId = prdObject.get("stpid").toString();
            String stopName = prdObject.get("stpnm").toString();
            String vehicleId = prdObject.get("vid").toString();
            String distance = prdObject.get("dstp").toString();
            String routeId = prdObject.get("rt").toString();
            String routeDir = prdObject.get("rtdir").toString();
            String destination = prdObject.get("des").toString();
            boolean delayed = Boolean.parseBoolean(prdObject.get("dly").toString().toLowerCase());
            String tmstp = prdObject.get("tmstmp").toString();
            String pred = prdObject.get("prdtm").toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm");
            Date timestamp = sdf.parse(tmstp);
            Date predTime = sdf.parse(pred);
            Prediction prd = new Prediction(timestamp, typ, stopId, stopName, vehicleId, distance
                    ,routeId, routeDir, destination, predTime, delayed);
            prdList.add(prd);
        }
        return prdList;
    }
    
    public List<BusDetails> getTransit(Stops origin, Stops destination) throws IOException, JSONException    {
        List<BusDetails> busList = new ArrayList<>();
        Map<String, String> params = new HashMap<>();
        String urlStr = Constants.Api.DIRECTIONS_URL + "?key=" + Constants.Api.DIRECTIONS_API_KEY;
        String dest = destination.getLat().toString() + "," + destination.getLon().toString();
        String orig = origin.getLat().toString() + "," + origin.getLon().toString();
        params.put("origin", orig);
        params.put("destination", dest);
        params.put("mode", "transit");
        StringBuilder sb = new StringBuilder(urlStr);
        for (String key : params.keySet())   {
            sb.append("&" + key + "=" + params.get(key).replaceAll(" ", "%20"));
        }
        urlStr = sb.toString();
        urlStr = "https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyC7lVdXpT63KbGeBeFVO5iRLZYajXxngP0&mode=transit&origin=40.44889,%20-79.92990&destination=40.54757,%20-80.01847";
        URL url = new URL(urlStr);
        URLConnection urlConn = url.openConnection();
        InputStream in = urlConn.getInputStream();
        BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));

        sb = new StringBuilder();
        String respLine;
        while ((respLine = res.readLine()) != null)   {
            sb.append(respLine);
        }
        String response = sb.toString();
        JSONObject obj = new JSONObject(response);
        JSONArray jRoutes = obj.getJSONArray("routes");
        for (int i = 0; i < jRoutes.length(); i++) {
            JSONObject rtObj = jRoutes.getJSONObject(i);
            JSONArray jLegs = rtObj.getJSONArray("legs");
            for (int j = 0; j < jLegs.length(); j++)    {
                JSONObject legObj = jLegs.getJSONObject(j);
                JSONArray jSteps = legObj.getJSONArray("steps");
                for (int k = 0; k < jSteps.length(); k++)   {
                    JSONObject stepObj = jSteps.getJSONObject(k);
                    String travelMode = stepObj.get("travel_mode").toString();
                    if (travelMode != null && travelMode.equals("TRANSIT")) {
                        JSONObject startLoc = (JSONObject) stepObj.get("start_location");
                        Double sourceLat = Double.parseDouble(startLoc.get("lat").toString());
                        Double sourceLong = Double.parseDouble(startLoc.get("lng").toString());
                        JSONObject destLoc = (JSONObject) stepObj.get("end_location");
                        Double destLat = Double.parseDouble(destLoc.get("lat").toString());
                        Double destLong = Double.parseDouble(destLoc.get("lng").toString());
                        
                        JSONObject transitDet = stepObj.getJSONObject("transit_details");
                        
                        int numStops = Integer.parseInt(transitDet.get("num_stops").toString());
                        JSONObject depTime = transitDet.getJSONObject("departure_time");
                        Date dep = new Date(Long.parseLong(depTime.get("value").toString()) * 1000);
                        JSONObject arrTime = transitDet.getJSONObject("arrival_time");
                        Date arr = new Date(Long.parseLong(arrTime.get("value").toString()) * 1000);
                        JSONObject line = transitDet.getJSONObject("line");
                        String routeNum = line.get("short_name").toString();
                        String headSign = transitDet.get("headsign").toString();
                        String[] parts = headSign.split("-");
                        String direction = parts[0];
                        JSONObject depStop = transitDet.getJSONObject("departure_stop");
                        String departureStop = depStop.get("name").toString();
                        JSONObject arrStop = transitDet.getJSONObject("arrival_stop");
                        String arrivalStop = arrStop.get("name").toString();
                        
                        BusDetails bus = new BusDetails(departureStop, routeNum, sourceLat, sourceLong, 
                                arrivalStop, destLat, destLong, 
                                dep, arr, direction, 
                                numStops);
                        busList.add(bus);
                        
                    }
                }
            }
        }
        return busList;
    }
}