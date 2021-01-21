package ntua.softeng28.evcharge.admin;

import com.fasterxml.jackson.annotation.JsonProperty;

import ntua.softeng28.evcharge.Operator.Operator;
import ntua.softeng28.evcharge.charging_station.Address;

public class ChargingPointData {
    @JsonProperty("OperatorInfo")
    private Operator operator;

    @JsonProperty("UsageCost")
    private Float usageCost;

    @JsonProperty("AddressInfo")
    private Address addressInfo;

    public ChargingPointData() {
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Float getUsageCost() {
        return usageCost;
    }

    public void setUsageCost(Float usageCost) {
        this.usageCost = usageCost;
    }

    public Address getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(Address addressInfo) {
        this.addressInfo = addressInfo;
    }

    @Override
    public String toString() {
        return "ChargingPointData [addressInfo=" + addressInfo + ", operator=" + operator + ", usageCost=" + usageCost
                + "]";
    }

   
    
}
