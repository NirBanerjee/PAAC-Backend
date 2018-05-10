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

    public String getRouteId() {
      return routeId;
    }

    public String getRouteDirection() {
      return routeDirection;
    }
}
