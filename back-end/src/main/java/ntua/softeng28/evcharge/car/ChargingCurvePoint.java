package ntua.softeng28.evcharge.car;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ChargingCurvePoint {

    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

    @JsonProperty("percentage")
    private int percentage;

    @JsonProperty("power")
    private int power;

    public ChargingCurvePoint(){

    }

    public ChargingCurvePoint(int percentage, int power) {
        this.percentage = percentage;
        this.power = power;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ChargingCurvePoint [id=" + id + ", percentage=" + percentage + ", power=" + power + "]";
    }

    @Override
    public int hashCode() {
       return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChargingCurvePoint other = (ChargingCurvePoint) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

  

   
}
