package ntua.softeng28.evcharge.service;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class SessionsPerEVCsvResponse {
	@CsvBindByPosition(position = 0)
    @JsonProperty("VehicleID")
	private final String vehicleID;
	
	@CsvBindByPosition(position = 1)
	@JsonProperty("RequestTimestamp")
	private final String requestTimestamp;

	@CsvBindByPosition(position = 2)
	@JsonProperty("PeriodFrom")
	private final String periodFrom;

	@CsvBindByPosition(position = 3)
	@JsonProperty("PeriodTo")
	private final String periodTo;

	@CsvBindByPosition(position = 4)
	@JsonProperty("TotalEnergyConsumed")
	private final Float totalEnergyConsumed;

	@CsvBindByPosition(position = 5)
	@JsonProperty("NumberOfVisitedPoints")
	private final Integer numberOfVisitedPoints;

	@CsvBindByPosition(position = 6)
    @JsonProperty("NumberOfVehicleChargingSessions")
	private final Integer numberOfVehicleChargingSessions;

	@CsvBindByPosition(position = 7)
    @JsonProperty("SessionIndex")
	private final Integer sessionIndex;

	@CsvBindByPosition(position = 8)
	@JsonProperty("SessionID")
	private final String sessionID;

	@CsvBindByPosition(position = 9)
	@JsonProperty("EnergyProvider")
	private final String energyProvider;

	@CsvBindByPosition(position = 10)
	@JsonProperty("StartedOn")
	private final String startedOn;

	@CsvBindByPosition(position = 11)
    @JsonProperty("FinishedOn")
	private final String finishedOn;

	@CsvBindByPosition(position = 12)
	@JsonProperty("ΕnergyDelivered")
	private final Float energyDelivered;

	@CsvBindByPosition(position = 13)
    @JsonProperty("PricePolicyRef")
	private final String pricePolicyRef;

	@CsvBindByPosition(position = 14)
    @JsonProperty("CostPerKWh")
	private final Float costPerKWh;

	@CsvBindByPosition(position = 15)
	@JsonProperty("SessionCost")
	private final Float sessionCost;

	public SessionsPerEVCsvResponse(String vehicleID, String requestTimestamp, String periodFrom, String periodTo,
			Float totalEnergyConsumed, Integer numberOfVisitedPoints, Integer numberOfVehicleChargingSessions,
			Integer sessionIndex, String sessionID, String energyProvider, String startedOn, String finishedOn,
			Float energyDelivered, String pricePolicyRef, Float costPerKWh, Float sessionCost) {
		this.vehicleID = vehicleID;
		this.requestTimestamp = requestTimestamp;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.totalEnergyConsumed = totalEnergyConsumed;
		this.numberOfVisitedPoints = numberOfVisitedPoints;
		this.numberOfVehicleChargingSessions = numberOfVehicleChargingSessions;
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


	public String getVehicleID() {
		return vehicleID;
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

	public Float getTotalEnergyConsumed() {
		return totalEnergyConsumed;
	}

	public Integer getNumberOfVisitedPoints() {
		return numberOfVisitedPoints;
	}

	public Integer getNumberOfVehicleChargingSessions() {
		return numberOfVehicleChargingSessions;
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
		return "SessionsPerEVCsvResponse [costPerKWh=" + costPerKWh + ", energyDelivered=" + energyDelivered
				+ ", energyProvider=" + energyProvider + ", finishedOn=" + finishedOn
				+ ", numberOfVehicleChargingSessions=" + numberOfVehicleChargingSessions + ", numberOfVisitedPoints="
				+ numberOfVisitedPoints + ", periodFrom=" + periodFrom + ", periodTo=" + periodTo + ", pricePolicyRef="
				+ pricePolicyRef + ", requestTimestamp=" + requestTimestamp + ", sessionCost=" + sessionCost
				+ ", sessionID=" + sessionID + ", sessionIndex=" + sessionIndex + ", startedOn=" + startedOn
				+ ", totalEnergyConsumed=" + totalEnergyConsumed + ", vehicleID=" + vehicleID + "]";
	}
}