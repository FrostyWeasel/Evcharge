package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

public class VehicleChargingSession {
	@CsvBindByName(column = "SessionIndex")
    @JsonProperty("SessionIndex")
	private final Integer sessionIndex;

	@CsvBindByName(column = "SessionID")
	@JsonProperty("SessionID")
	private final String sessionID;

	@CsvBindByName(column = "EnergyProvider")
	@JsonProperty("EnergyProvider")
	private final String energyProvider;

	@CsvBindByName(column = "StartedOn")
	@JsonProperty("StartedOn")
	private final String startedOn;

	@CsvBindByName(column = "FinishedOn")
    @JsonProperty("FinishedOn")
	private final String finishedOn;

	@CsvBindByName(column = "ΕnergyDelivered")
	@JsonProperty("ΕnergyDelivered")
	private final Float energyDelivered;

	@CsvBindByName(column = "PricePolicyRef")
    @JsonProperty("PricePolicyRef")
	private final String pricePolicyRef;

	@CsvBindByName(column = "CostPerKWh")
    @JsonProperty("CostPerKWh")
	private final Float costPerKWh;

	@CsvBindByName(column = "SessionCost")
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
