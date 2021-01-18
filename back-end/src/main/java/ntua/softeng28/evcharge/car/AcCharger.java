package ntua.softeng28.evcharge.car;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class AcCharger {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    @JsonProperty("usable_phases")
    private int usable_phases;

    @JsonProperty("ports")
    private String[] ports;

    @JsonProperty("max_power")
    private float max_power;

    @JsonProperty("power_per_charging_point")
    private PowerPerChargingPoint power_per_charging_point;


    public AcCharger() {

    }

    public AcCharger(int usable_phases, String[] ports, float max_power,
            PowerPerChargingPoint power_per_charging_point) {
        this.usable_phases = usable_phases;
        this.ports = ports;
        this.max_power = max_power;
        this.power_per_charging_point = power_per_charging_point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUsable_phases() {
        return usable_phases;
    }

    public void setUsable_phases(int usable_phases) {
        this.usable_phases = usable_phases;
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

    public PowerPerChargingPoint getPower_per_charging_point() {
        return power_per_charging_point;
    }

    public void setPower_per_charging_point(PowerPerChargingPoint power_per_charging_point) {
        this.power_per_charging_point = power_per_charging_point;
    }

    @Override
    public String toString() {
        return "AcCharger [id=" + id + ", max_power=" + max_power + ", ports=" + Arrays.toString(ports)
                + ", power_per_charging_point=" + power_per_charging_point + ", usable_phases=" + usable_phases + "]";
    }

}
