package ntua.softeng28.evcharge.car;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class EnergyConsumption {
    @JsonProperty("average_consumption")
    private int average_consumption;

    public EnergyConsumption() {
        
    }
   
    public EnergyConsumption(int average_consumption) {
        this.average_consumption = average_consumption;
    }

    public int getAverage_consumption() {
        return average_consumption;
    }

    public void setAverage_consumption(int average_consumption) {
        this.average_consumption = average_consumption;
    }

    @Override
    public String toString() {
        return "EnergyConsumption [average_consumption=" + average_consumption + "]";
    }

}
