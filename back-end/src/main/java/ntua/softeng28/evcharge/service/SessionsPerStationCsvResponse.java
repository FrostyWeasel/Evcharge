package ntua.softeng28.evcharge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;

import ntua.softeng28.evcharge.session.Session;

public class SessionsPerStationCsvResponse {
    @CsvBindByPosition(position = 0)
    @JsonProperty("StationID")
	private final String stationID;

    @CsvBindByPosition(position = 1)
	@JsonProperty("Operator")
	private final String operator;

    @CsvBindByPosition(position = 2)
	@JsonProperty("RequestTimestamp")
	private final String requestTimestamp;

    @CsvBindByPosition(position = 3)
	@JsonProperty("PeriodFrom")
	private final String periodFrom;

    @CsvBindByPosition(position = 4)
	@JsonProperty("PeriodTo")
	private final String periodTo;

    @CsvBindByPosition(position = 5)
	@JsonProperty("TotalEnergyDelivered")
	private final Float totalEnergyDelivered;

    @CsvBindByPosition(position = 6)
	@JsonProperty("NumberOfChargingSessions")
	private final Long numberOfChargingSessions;

    @CsvBindByPosition(position = 7)
	@JsonProperty("NumberOfActivePoints")
	private final Long numberOfActivePoints;

    @CsvBindByPosition(position = 8)
    @JsonProperty("PointID")
	private final Long pointID;

    @CsvBindByPosition(position = 9)
	@JsonProperty("PointSessions")
	private final Integer pointSessions;

    @CsvBindByPosition(position = 10)
	@JsonProperty("EnergyDelivered")
	private final Float energyDelivered;

	public SessionsPerStationCsvResponse(String stationID, String operator, String requestTimestamp, String periodFrom,
			String periodTo, Float totalEnergyDelivered, Long numberOfChargingSessions, Long numberOfActivePoints,
			Long pointID, Integer pointSessions, Float energyDelivered) {
		this.stationID = stationID;
		this.operator = operator;
		this.requestTimestamp = requestTimestamp;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.totalEnergyDelivered = totalEnergyDelivered;
		this.numberOfChargingSessions = numberOfChargingSessions;
		this.numberOfActivePoints = numberOfActivePoints;
		this.pointID = pointID;
		this.pointSessions = pointSessions;
		this.energyDelivered = energyDelivered;
	}

	public String getStationID() {
		return stationID;
	}

	public String getOperator() {
		return operator;
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

	public Float getTotalEnergyDelivered() {
		return totalEnergyDelivered;
	}

	public Long getNumberOfChargingSessions() {
		return numberOfChargingSessions;
	}

	public Long getNumberOfActivePoints() {
		return numberOfActivePoints;
	}

	public Long getPointID() {
		return pointID;
	}

	public Integer getPointSessions() {
		return pointSessions;
	}

	public Float getEnergyDelivered() {
		return energyDelivered;
	}

	@Override
	public String toString() {
		return "SessionsPerStationCsvResponse [energyDelivered=" + energyDelivered + ", numberOfActivePoints="
				+ numberOfActivePoints + ", numberOfChargingSessions=" + numberOfChargingSessions + ", operator="
				+ operator + ", periodFrom=" + periodFrom + ", periodTo=" + periodTo + ", pointID=" + pointID
				+ ", pointSessions=" + pointSessions + ", requestTimestamp=" + requestTimestamp + ", stationID="
				+ stationID + ", totalEnergyDelivered=" + totalEnergyDelivered + "]";
	}
}
