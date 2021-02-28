package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;

public class SessionsPerProviderCsvResponse {
    @CsvBindByPosition(position = 0)
    @JsonProperty("ProviderID")
	private final String providerID;

    @CsvBindByPosition(position = 1)
	@JsonProperty("ProviderName")
	private final String providerName;

    @CsvBindByPosition(position = 2)
	@JsonProperty("StationID")
	private final String stationID;

    @CsvBindByPosition(position = 3)
	@JsonProperty("SessionID")
	private final Long sessionID;

    @CsvBindByPosition(position = 4)
	@JsonProperty("VehicleID")
	private final String vehicleID;

    @CsvBindByPosition(position = 5)
	@JsonProperty("StartedOn")
	private final String startedOn;

    @CsvBindByPosition(position = 6)
    @JsonProperty("FinishedOn")
	private final String finishedOn;

    @CsvBindByPosition(position = 7)
    @JsonProperty("EnergyDelivered")
	private final Float energyDelivered;

    @CsvBindByPosition(position = 8)
    @JsonProperty("PricePolicyRef")
	private final String pricePolicyRef;

    @CsvBindByPosition(position = 9)
    @JsonProperty("CostPerKWh")
	private final Float costPerKWh;

    @CsvBindByPosition(position = 10)
    @JsonProperty("TotalCost")
	private final Float totalCost;

	public SessionsPerProviderCsvResponse(String providerID, String providerName, String stationID, Long sessionID,
			String vehicleID, String startedOn, String finishedOn, Float energyDelivered, String pricePolicyRef,
			Float costPerKWh, Float totalCost) {
		this.providerID = providerID;
		this.providerName = providerName;
		this.stationID = stationID;
		this.sessionID = sessionID;
		this.vehicleID = vehicleID;
		this.startedOn = startedOn;
		this.finishedOn = finishedOn;
		this.energyDelivered = energyDelivered;
		this.pricePolicyRef = pricePolicyRef;
		this.costPerKWh = costPerKWh;
		this.totalCost = totalCost;
	}

	public String getProviderID() {
		return providerID;
	}

	public String getProviderName() {
		return providerName;
	}

	public String getStationID() {
		return stationID;
	}

	public Long getSessionID() {
		return sessionID;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public String getStartedOn() {
		return startedOn;
	}

	public String getFinishedOn() {
		return finishedOn;
	}

	public Float getEnergyDelivered() {
		return energyDelivered;
	}

	public String getPricePolicyRef() {
		return pricePolicyRef;
	}

	public Float getCostPerKWh() {
		return costPerKWh;
	}

	public Float getTotalCost() {
		return totalCost;
	}

	@Override
	public String toString() {
		return "SessionsPerProviderCsvResponse [costPerKWh=" + costPerKWh + ", energyDelivered=" + energyDelivered
				+ ", finishedOn=" + finishedOn + ", pricePolicyRef=" + pricePolicyRef + ", providerID=" + providerID
				+ ", providerName=" + providerName + ", sessionID=" + sessionID + ", startedOn=" + startedOn
				+ ", stationID=" + stationID + ", totalCost=" + totalCost + ", vehicleID=" + vehicleID + "]";
	}
}
