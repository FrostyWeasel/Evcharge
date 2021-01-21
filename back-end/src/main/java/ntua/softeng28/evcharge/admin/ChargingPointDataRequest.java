package ntua.softeng28.evcharge.admin;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargingPointDataRequest {
    @JsonProperty("data")
    private ChargingPointData[] pointData;

    public ChargingPointDataRequest() {
    }

    public ChargingPointData[] getPointData() {
        return pointData;
    }

    public void setPointData(ChargingPointData[] pointData) {
        this.pointData = pointData;
    }

    @Override
    public String toString() {
        return "PointDataRequest [pointData=" + Arrays.toString(pointData) + "]";
    }
    
}
