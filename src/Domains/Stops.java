package Domains;

public class Stops {
    
    private String stpid;
    private String stpnm;
    private Double lat;
    private Double lon;

    public Stops(String stpid, String stpnm, Double lat, Double lon) {
        this.stpid = stpid;
        this.stpnm = stpnm;
        this.lat = lat;
        this.lon = lon;
    }

    public String getStpid() {
        return stpid;
    }

    public void setStpid(String stpid) {
        this.stpid = stpid;
    }

    public String getStpnm() {
        return stpnm;
    }

    public void setStpnm(String stpnm) {
        this.stpnm = stpnm;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
