package ntua.softeng28.evcharge.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ntua.softeng28.evcharge.session.Session;

public class SessionsPerStationResponse {
	@JsonProperty("StationID")
	private final String stationID;

	@JsonProperty("Operator")
	private final String operator;

	@JsonProperty("RequestTimestamp")
	private final String requestTimestamp;

	@JsonProperty("PeriodFrom")
	private final String periodFrom;

	@JsonProperty("PeriodTo")
	private final String periodTo;

	@JsonProperty("TotalEnergyDelivered")
	private final Float totalEnergyDelivered;

	@JsonProperty("NumberOfChargingSessions")
	private final Long numberOfChargingSessions;

	@JsonProperty("NumberOfActivePoints")
	private final Long numberOfActivePoints;

	@JsonProperty("SessionsSummaryList")
	private final SessionsSummary[] sessionsSummary;

	public SessionsPerStationResponse(String stationID, String operator, String requestTimestamp, String periodFrom,
			String periodTo, Float totalEnergyDelivered, Long numberOfChargingSessions, Long numberOfActivePoints,
			List<SessionsSummary> sessionsSummary) {

		this.stationID = stationID;
		this.operator = operator;
		this.requestTimestamp = requestTimestamp;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.totalEnergyDelivered = totalEnergyDelivered;
		this.numberOfChargingSessions = numberOfChargingSessions;
		this.numberOfActivePoints = numberOfActivePoints;

		this.sessionsSummary = sessionsSummary.toArray(new SessionsSummary[0]);
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

	public SessionsSummary[] getSessionsSummary() {
		return sessionsSummary;
	}

	@Override
	public String toString() {
		return "SessionsPerStationResponse [numberOfActivePoints=" + numberOfActivePoints
				+ ", numberOfChargingSessions=" + numberOfChargingSessions + ", operator=" + operator + ", periodFrom="
				+ periodFrom + ", periodTo=" + periodTo + ", requestTimestamp=" + requestTimestamp
				+ ", sessionsSummary=" + Arrays.toString(sessionsSummary) + ", stationID=" + stationID
				+ ", totalEnergyDelivered=" + totalEnergyDelivered + "]";
	}

}