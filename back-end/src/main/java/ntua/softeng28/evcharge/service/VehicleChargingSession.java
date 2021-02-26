package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleChargingSession {
    @JsonProperty("SessionIndex")
	private final Integer sessionIndex;

	@JsonProperty("SessionID")
	private final String sessionID;

	@JsonProperty("EnergyProvider")
	private final String energyProvider;

	@JsonProperty("StartedOn")
	private final String startedOn;

    @JsonProperty("FinishedOn")
	private final String finishedOn;

	@JsonProperty("ΕnergyDelivered")
	private final Float energyDelivered;

    @JsonProperty("PricePolicyRef")
	private final String pricePolicyRef;

    @JsonProperty("CostPerKWh")
	private final Float costPerKWh;

	@JsonProperty("SessionCost")
	private final Float sessionCost;

	public VehicleChargingSession(Integer sessionIndex, String sessionID, String energyProvider, String startedOn,
			String finishedOn, Float energyDelivered, String pricePolicyRef, Float costPerKWh, Float sessionCost) {
		this.sessionIndex = sessionIndex;
		this.sessionID = sessionID;
		this.energyProvider = energyProvider;
		this.startedOn = startedOn;
		this.finishedOn = finishedOn;
		this.energyDelivered = energyDelivered;
		this.pricePolicyRef = pricePolicyRef;
		this.costPerKWh = costPerKWh;
		this.sessionCost = sessionCost;
	}

	public Integer getSessionIndex() {
		return sessionIndex;
	}

	public String getSessionID() {
		return sessionID;
	}

	public String getEnergyProvider() {
		return energyProvider;
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

	public Float getSessionCost() {
		return sessionCost;
	}

	@Override
	public String toString() {
		return "VehicleChargingSessions [costPerKWh=" + costPerKWh + ", energyDelivered=" + energyDelivered
				+ ", energyProvider=" + energyProvider + ", finishedOn=" + finishedOn + ", pricePolicyRef="
				+ pricePolicyRef + ", sessionCost=" + sessionCost + ", sessionID=" + sessionID + ", sessionIndex="
				+ sessionIndex + ", startedOn=" + startedOn + "]";
	}
}
