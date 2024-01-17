import com.yast.util.Constants;
import com.yast.util.Utils;
import java.util.Date;

public class ActivityEntity {

    int id;
    int activityType;
    int hartRate;
    int hartBatNo;
    int distance;
    int speed;
    int strides;
    String startDateTime;
    String endDateTime;
    String currentDateTime;

    public ActivityEntity(){

    }
    // constructor
    public ActivityEntity(int Id, int activityType, int hartRate, int _hartBatNo, int distance, int speed, int strides, String startDateTime, String endDateTime, String currentDateTime){
        this.id = Id;
        this.activityType = activityType;
        this.hartRate = hartRate;
        this.hartBatNo = _hartBatNo;
        this.distance = distance;
        this.speed = speed;
        this.strides = strides;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.currentDateTime = currentDateTime;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return this.id;
    }
    public void setActivityType(int activityType){
        this.activityType = activityType;
    }
    public int getActivityType(){
        return this.activityType;
    }
    public void setHartRate(int hartRate){
        this.hartRate = hartRate;
    }

    public int getHartRate(){
        return this.hartRate;
    }
    public void setHartBatNo(int hartBatNo){
        this.hartBatNo = hartBatNo;
    }
    public int getHartBatNo(){
        return this.hartBatNo;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public int getDistance(){
        return this.distance;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setStrides(int strides){
        this.strides = strides;
    }

    public int getStrides(){
        return this.strides;
    }

    public void setStartDateTime(String startDateTime){
        this.startDateTime = startDateTime;
    }

    public String getStartDateTime(){
        return this.startDateTime;
    }

    public void setEndDateTime(String endDateTime){
        this.endDateTime = endDateTime;
    }

    public String getEndDateTime(){
        return this.endDateTime;
    }

    public void set_currentDateTime(String currentDateTime){
        this.currentDateTime = currentDateTime;
    }
    public String getCurrentDateTime(){
        return this.currentDateTime;
    }

    @Override
    public String toString() {
        return "ActivityEntity{" +
                "id=" + id +
                ", activityType=" + activityType +
                ", hartRate=" + hartRate +
                ", hartBatNo=" + hartBatNo +
                ", distance=" + distance +
                ", speed=" + speed +
                ", strides=" + strides +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", currentDateTime='" + currentDateTime + '\'' +
                '}';
    }
}