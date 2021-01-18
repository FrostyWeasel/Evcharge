package ntua.softeng28.evcharge.car;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DcChargerRequest {
    
    @JsonProperty("ports")
    private String[] ports;

    @JsonProperty("max_power")
    private float max_power;

    @JsonProperty("charging_curve")
    private ChargingCurvePoint[] charging_curve;

    @JsonProperty("is_default_charging_curve")
    private Boolean is_default_charging_curve;

    public DcChargerRequest(){

    }

    public DcChargerRequest(String[] ports, float max_power, ChargingCurvePoint[] charging_curve,
            Boolean is_default_charging_curve) {
        this.ports = ports;
        this.max_power = max_power;
        this.charging_curve = charging_curve;
        this.is_default_charging_curve = is_default_charging_curve;
    }

    public String[] getPorts() {
        return ports;
    }

    public void setPorts(String[] ports) {
        this.ports = ports;
    }

    public float getMax_power() {
        return max_power;
    }

    public void setMax_power(float max_power) {
        this.max_power = max_power;
    }

    public ChargingCurvePoint[] getCharging_curve() {
        return charging_curve;
    }

    public void setCharging_curve(ChargingCurvePoint[] charging_curve) {
        this.charging_curve = charging_curve;
    }

    public Boolean getIs_default_charging_curve() {
        return is_default_charging_curve;
    }

    public void setIs_default_charging_curve(Boolean is_default_charging_curve) {
        this.is_default_charging_curve = is_default_charging_curve;
    }

    @Override
    public String toString() {
        return "DcChargerRequest [charging_curve=" + Arrays.toString(charging_curve) + ", is_default_charging_curve="
                + is_default_charging_curve + ", max_power=" + max_power + ", ports=" + Arrays.toString(ports) + "]";
    }

 

}
