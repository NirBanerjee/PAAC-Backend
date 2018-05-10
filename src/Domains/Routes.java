package Domains;

public class Routes {
    
    private String rt; 
    private String rtnm; 
    private String rtclr; 

    public Routes(String rt, String rtnm, String rtclr) {
        this.rt = rt;
        this.rtnm = rtnm;
        this.rtclr = rtclr;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRtnm() {
        return rtnm;
    }

    public void setRtnm(String rtnm) {
        this.rtnm = rtnm;
    }

    public String getRtclr() {
        return rtclr;
    }

    public void setRtclr(String rtclr) {
        this.rtclr = rtclr;
    }


}
