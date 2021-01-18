package ntua.softeng28.evcharge.car;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class PowerPerChargingPoint {

    @JsonProperty("2.0")
    private float two_point_zero;

    @JsonProperty("2.3")
    private float two_point_three;

    @JsonProperty("3.7")
    private float three_point_seven;

    @JsonProperty("7.4")
    private float seven_point_four;

    @JsonProperty("11")
    private float eleven;

    @JsonProperty("16")
    private float sixteen;

    @JsonProperty("22")
    private float twentytwo;

    @JsonProperty("43")
    private float fortythree;

    public PowerPerChargingPoint(){

    }

    public PowerPerChargingPoint(float two_point_zero, float two_point_three, float three_point_seven, float seven_point_four,
            float eleven, float sixteen, float twentytwo, float fortythree) {
        this.two_point_zero = two_point_zero;
        this.two_point_three = two_point_three;
        this.three_point_seven = three_point_seven;
        this.seven_point_four = seven_point_four;
        this.eleven = eleven;
        this.sixteen = sixteen;
        this.twentytwo = twentytwo;
        this.fortythree = fortythree;
    }

    public float getTwo_point_zero() {
        return two_point_zero;
    }

    public void setTwo_point_zero(float two_point_zero) {
        this.two_point_zero = two_point_zero;
    }

    public float getTwo_point_three() {
        return two_point_three;
    }

    public void setTwo_point_three(float two_point_three) {
        this.two_point_three = two_point_three;
    }

    public float getThree_point_seven() {
        return three_point_seven;
    }

    public void setThree_point_seven(float three_point_seven) {
        this.three_point_seven = three_point_seven;
    }

    public float getSeven_point_four() {
        return seven_point_four;
    }

    public void setSeven_point_four(float seven_point_four) {
        this.seven_point_four = seven_point_four;
    }

    public float getEleven() {
        return eleven;
    }

    public void setEleven(float eleven) {
        this.eleven = eleven;
    }

    public float getSixteen() {
        return sixteen;
    }

    public void setSixteen(float sixteen) {
        this.sixteen = sixteen;
    }

    public float getTwentytwo() {
        return twentytwo;
    }

    public void setTwentytwo(float twentytwo) {
        this.twentytwo = twentytwo;
    }

    public float getFortythree() {
        return fortythree;
    }

    public void setFortythree(float fortythree) {
        this.fortythree = fortythree;
    }

    @Override
    public String toString() {
        return "PowerPerChargingPoint [eleven=" + eleven + ", fortythree=" + fortythree + ", seven_point_four="
                + seven_point_four + ", sixteen=" + sixteen + ", three_point_seven=" + three_point_seven
                + ", twentytwo=" + twentytwo + ", two_point_three=" + two_point_three + ", two_point_zero="
                + two_point_zero + "]";
    }
}
