package Domains;
import java.util.Date;
/**
 * @author nirmoho-Mac
 *
 */
public class Prediction {
    
    private Date timestamp;

    private String type;

    private String stopId;

    private String stopName;

    private String vehicleId;

    private String distance;

    private String routeId;

    private String routeDirection;
    
    private String destination;

    private Date predictionTime;

    private boolean delayed;
    
    

    /**
     * @param timestamp
     * @param type
     * @param stopId
     * @param stopName
     * @param vehicleId
     * @param distance
     * @param routeId
     * @param routeDirection
     * @param destination
     * @param predictionTime
     * @param delayed
     */
    public Prediction(Date timestamp, String type, String stopId, String stopName, String vehicleId,
            String distance, String routeId, String routeDirection, String destination, Date predictionTime,
            boolean delayed) {
        super();
        this.timestamp = timestamp;
        this.type = type;
        this.stopId = stopId;
        this.stopName = stopName;
        this.vehicleId = vehicleId;
        this.distance = distance;
        this.routeId = routeId;
        this.routeDirection = routeDirection;
        this.destination = destination;
        this.predictionTime = predictionTime;
        this.delayed = delayed;
    }

    public String getRouteId() {
      return routeId;
    }

    public String getRouteDirection() {
      return routeDirection;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the stopId
     */
    public String getStopId() {
        return stopId;
    }

    /**
     * @return the stopName
     */
    public String getStopName() {
        return stopName;
    }

    /**
     * @return the vehicleId
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * @return the distance
     */
    public String getDistance() {
        return distance;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @return the predictionTime
     */
    public Date getPredictionTime() {
        return predictionTime;
    }

    /**
     * @return the delayed
     */
    public boolean isDelayed() {
        return delayed;
    }
    
    @Override
    public String toString()    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.stopName).append("  ").append(this.predictionTime);
        return sb.toString();
    }
    
}
