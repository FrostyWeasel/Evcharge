package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;

public class UserReportCsvResponse {
    @JsonProperty("Username")
    @CsvBindByPosition(position = 0)
    private final String username;

    @JsonProperty("RequestTimestamp")
	@CsvBindByPosition(position = 1)
    private final String requestTimestamp;

	@JsonProperty("PeriodFrom")
	@CsvBindByPosition(position = 2)
    private final String periodFrom;

	@JsonProperty("PeriodTo")
	@CsvBindByPosition(position = 3)
    private final String periodTo;

    @JsonProperty("TotalCost")
	@CsvBindByPosition(position = 4)
    private final Float totalCost;

	@JsonProperty("TotalSessions")
	@CsvBindByPosition(position = 5)
    private final Integer totalSessions;

	@JsonProperty("TotalCars")
	@CsvBindByPosition(position = 6)
    private final Integer totalCars;

    @JsonProperty("TotalEnergy")
	@CsvBindByPosition(position = 7)
    private final Float totalEnergy;

    @JsonProperty("VehicleID")
    @CsvBindByPosition(position = 8)
	private final String vehicleID;

    @JsonProperty("BrandName")
    @CsvBindByPosition(position = 9)
    private final String brandName;

    @JsonProperty("Model")
    @CsvBindByPosition(position = 10)
    private  final String model;

    @JsonProperty("Cost")
	@CsvBindByPosition(position = 11)
    private final Float cost;

	@JsonProperty("Sessions")
	@CsvBindByPosition(position = 12)
    private final Integer sessions;

    @JsonProperty("EnergyDelivered")
	@CsvBindByPosition(position = 13)
    private final Float energyDelivered;

    public UserReportCsvResponse(String username, String requestTimestamp, String periodFrom, String periodTo,
            Float totalCost, Integer totalSessions, Integer totalCars, Float totalEnergy, String vehicleID,
            String brandName, String model, Float cost, Integer sessions, Float energyDelivered) {
        this.username = username;
        this.requestTimestamp = requestTimestamp;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
        this.totalCost = totalCost;
        this.totalSessions = totalSessions;
        this.totalCars = totalCars;
        this.totalEnergy = totalEnergy;
        this.vehicleID = vehicleID;
        this.brandName = brandName;
        this.model = model;
        this.cost = cost;
        this.sessions = sessions;
        this.energyDelivered = energyDelivered;
    }

    public String getUsername() {
        return username;
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public String getPeriodFrom() {
        return periodFrom;
    }

    public String getPeriodTo() {
        return periodTo;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public Integer getTotalSessions() {
        return totalSessions;
    }

    public Integer getTotalCars() {
        return totalCars;
    }

    public Float getTotalEnergy() {
        return totalEnergy;
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
        return "UserReportCsvResponse [brandName=" + brandName + ", cost=" + cost + ", energyDelivered="
                + energyDelivered + ", model=" + model + ", periodFrom=" + periodFrom + ", periodTo=" + periodTo
                + ", requestTimestamp=" + requestTimestamp + ", sessions=" + sessions + ", totalCars=" + totalCars
                + ", totalCost=" + totalCost + ", totalEnergy=" + totalEnergy + ", totalSessions=" + totalSessions
                + ", username=" + username + ", vehicleID=" + vehicleID + "]";
    }

    
}
