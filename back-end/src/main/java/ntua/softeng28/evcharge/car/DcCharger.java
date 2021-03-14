package ntua.softeng28.evcharge.car;

import java.util.Arrays;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class DcCharger {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    
    @JsonProperty("ports")
    private String[] ports;

    @JsonProperty("max_power")
    private float max_power;

    @JsonProperty("charging_curve")
    @OneToMany
    private Set<ChargingCurvePoint> charging_curve;

    @JsonProperty("is_default_charging_curve")
    private Boolean is_default_charging_curve;

    public DcCharger(){

    }

    public DcCharger(String[] ports, float max_power, Set<ChargingCurvePoint> charging_curve,
            Boolean is_default_charging_curve) {
        this.ports = ports;
        this.max_power = max_power;
        this.charging_curve = charging_curve;
        this.is_default_charging_curve = is_default_charging_curve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<ChargingCurvePoint> getCharging_curve() {
        return charging_curve;
    }

    public void setCharging_curve(Set<ChargingCurvePoint> charging_curve) {
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
        return "DcCharger [charging_curve=" + charging_curve + ", id=" + id + ", is_default_charging_curve="
                + is_default_charging_curve + ", max_power=" + max_power + ", ports=" + Arrays.toString(ports) + "]";
    }

}
