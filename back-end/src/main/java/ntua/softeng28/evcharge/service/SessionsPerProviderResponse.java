package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionsPerProviderResponse {
    @JsonProperty("ProviderID")
	private final String providerID;

	@JsonProperty("ProviderName")
	private final String providerName;

	@JsonProperty("StationID")
	private final String stationID;

	@JsonProperty("SessionID")
	private final Long sessionID;

	@JsonProperty("VehicleID")
	private final String vehicleID;

	@JsonProperty("StartedOn")
	private final String startedOn;

    @JsonProperty("FinishedOn")
	private final String finishedOn;

    @JsonProperty("EnergyDelivered")
	private final Float energyDelivered;

    @JsonProperty("PricePolicyRef")
	private final String pricePolicyRef;

    @JsonProperty("CostPerKWh")
	private final Float costPerKWh;

    @JsonProperty("TotalCost")
	private final Float totalCost;

	

	public SessionsPerProviderResponse(String providerID, String providerName, String stationID, Long sessionID,
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
		return "SessionsPerProviderResponse [costPerKWh=" + costPerKWh + ", energyDelivered=" + energyDelivered
				+ ", finishedOn=" + finishedOn + ", pricePolicyRef=" + pricePolicyRef + ", providerID=" + providerID
				+ ", providerName=" + providerName + ", sessionID=" + sessionID + ", startedOn=" + startedOn
				+ ", stationID=" + stationID + ", totalCost=" + totalCost + ", vehicleID=" + vehicleID + "]";
	}
}
