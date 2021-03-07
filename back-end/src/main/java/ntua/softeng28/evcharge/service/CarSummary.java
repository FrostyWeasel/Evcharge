package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarSummary {
    @JsonProperty("VehicleID")
	private final String vehicleID;

    @JsonProperty("BrandName")
    private final String brandName;

    @JsonProperty("Model")
    private  final String model;

    @JsonProperty("Cost")
	private final Float cost;

	@JsonProperty("Sessions")
	private final Integer sessions;

    @JsonProperty("EnergyDelivered")
	private final Float energyDelivered;

    public CarSummary(String vehicleID, String brandName, String model, Float cost, Integer sessions,
            Float energyDelivered) {
        this.vehicleID = vehicleID;
        this.brandName = brandName;
        this.model = model;
        this.cost = cost;
        this.sessions = sessions;
        this.energyDelivered = energyDelivered;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getModel() {
        return model;
    }

    public Float getCost() {
        return cost;
    }

    public Integer getSessions() {
        return sessions;
    }

    public Float getEnergyDelivered() {
        return energyDelivered;
    }

    @Override
    public String toString() {
        return "CarSummary [brandName=" + brandName + ", cost=" + cost + ", energyDelivered=" + energyDelivered
                + ", model=" + model + ", sessions=" + sessions + ", vehicleID=" + vehicleID + "]";
    }




}
