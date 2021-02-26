package ntua.softeng28.evcharge.service;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionsPerEVResponse {
    @JsonProperty("VehicleID")
	private final String vehicleID;

	@JsonProperty("RequestTimestamp")
	private final String requestTimestamp;

	@JsonProperty("PeriodFrom")
	private final String periodFrom;

	@JsonProperty("PeriodTo")
	private final String periodTo;

	@JsonProperty("TotalEnergyConsumed")
	private final Float totalEnergyConsumed;

	@JsonProperty("NumberOfVisitedPoints")
	private final Integer numberOfVisitedPoints;

    @JsonProperty("NumberOfVehicleChargingSessions")
	private final Integer numberOfVehicleChargingSessions;

    @JsonProperty("VehicleChargingSessionsList")
	private final VehicleChargingSession[] vehicleChargingSessionsList;

	public SessionsPerEVResponse(String vehicleID, String requestTimestamp, String periodFrom, String periodTo,
			Float totalEnergyConsumed, Integer numberOfVisitedPoints, Integer numberOfVehicleChargingSessions,
			VehicleChargingSession[] vehicleChargingSessionsList) {
		this.vehicleID = vehicleID;
		this.requestTimestamp = requestTimestamp;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.totalEnergyConsumed = totalEnergyConsumed;
		this.numberOfVisitedPoints = numberOfVisitedPoints;
		this.numberOfVehicleChargingSessions = numberOfVehicleChargingSessions;
		this.vehicleChargingSessionsList = vehicleChargingSessionsList;
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

	public VehicleChargingSession[] getVehicleChargingSessionsList() {
		return vehicleChargingSessionsList;
	}

	@Override
	public String toString() {
		return "SessionsPerEVResponse [numberOfVehicleChargingSessions=" + numberOfVehicleChargingSessions
				+ ", numberOfVisitedPoints=" + numberOfVisitedPoints + ", periodFrom=" + periodFrom + ", periodTo="
				+ periodTo + ", requestTimestamp=" + requestTimestamp + ", totalEnergyConsumed=" + totalEnergyConsumed
				+ ", vehicleChargingSessionsList=" + Arrays.toString(vehicleChargingSessionsList) + ", vehicleID="
				+ vehicleID + "]";
	}
}
