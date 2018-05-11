package Domains;

import java.util.Date;

public class BusDetails {
    
    private String sourceName;
    private String routeNum; //
    private Double sourceLat; //
    private Double sourceLon; //
    private String destName;
    private Double destLat; //
    private Double destLon; //
    private Date depTime; //
    private Date arrivalTime; //
    private String direction; //
    private int numOfStops; //
    
    
    
    /**
     * @param sourceName
     * @param routeNum
     * @param sourceLat
     * @param sourceLon
     * @param destName
     * @param destLat
     * @param destLon
     * @param depTime
     * @param arrivalTime
     * @param direction
     * @param numOfStops
     */
    public BusDetails(String sourceName, String routeNum, Double sourceLat, Double sourceLon, String destName,
            Double destLat, Double destLon, Date depTime, Date arrivalTime, String direction, int numOfStops) {
        this.sourceName = sourceName;
        this.routeNum = routeNum;
        this.sourceLat = sourceLat;
        this.sourceLon = sourceLon;
        this.destName = destName;
        this.destLat = destLat;
        this.destLon = destLon;
        this.depTime = depTime;
        this.arrivalTime = arrivalTime;
        this.direction = direction;
        this.numOfStops = numOfStops;
    }
    /**
     * @return the sourceName
     */
    public String getSourceName() {
        return sourceName;
    }
    /**
     * @param sourceName the sourceName to set
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    /**
     * @return the destName
     */
    public String getDestName() {
        return destName;
    }
    /**
     * @param destName the destName to set
     */
    public void setDestName(String destName) {
        this.destName = destName;
    }
    /**
     * @return the depTime
     */
    public Date getDepTime() {
        return depTime;
    }
    /**
     * @param depTime the depTime to set
     */
    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }
    /**
     * @return the arrivalTime
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }
    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    /**
     * @return the direction
     */
    public String getBusDirection() {
        return direction;
    }
    /**
     * @param direction the direction to set
     */
    public void setBusDirection(String direction) {
        this.direction = direction;
    }
    /**
     * @return the sourceLat
     */
    public Double getSourceLat() {
        return sourceLat;
    }
    /**
     * @param sourceLat the sourceLat to set
     */
    public void setSourceLat(Double sourceLat) {
        this.sourceLat = sourceLat;
    }
    /**
     * @return the sourceLon
     */
    public Double getSourceLon() {
        return sourceLon;
    }
    /**
     * @param sourceLon the sourceLon to set
     */
    public void setSourceLon(Double sourceLon) {
        this.sourceLon = sourceLon;
    }
    /**
     * @return the destLat
     */
    public Double getDestLat() {
        return destLat;
    }
    /**
     * @param destLat the destLat to set
     */
    public void setDestLat(Double destLat) {
        this.destLat = destLat;
    }
    /**
     * @return the destLon
     */
    public Double getDestLon() {
        return destLon;
    }
    /**
     * @param destLon the destLon to set
     */
    public void setDestLon(Double destLon) {
        this.destLon = destLon;
    }
    
    @Override
    public String toString()    {
        StringBuilder sb = new StringBuilder("");
        sb.append(this.sourceName).append("   ").append(this.depTime).append("   ");
        sb.append(this.destName).append("  ").append(this.arrivalTime).append("  ");
        sb.append(this.routeNum).append("   ").append(this.direction);
        
        return sb.toString();
    }
    /**
     * @return the routeNum
     */
    public String getRouteNum() {
        return routeNum;
    }
    /**
     * @param routeNum the routeNum to set
     */
    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum;
    }
    /**
     * @return the numOfStops
     */
    public int getNumOfStops() {
        return numOfStops;
    }
    /**
     * @param numOfStops the numOfStops to set
     */
    public void setNumOfStops(int numOfStops) {
        this.numOfStops = numOfStops;
    }
}
