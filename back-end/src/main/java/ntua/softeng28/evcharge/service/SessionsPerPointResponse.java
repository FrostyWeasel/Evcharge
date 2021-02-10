package ntua.softeng28.evcharge.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import ntua.softeng28.evcharge.session.Session;

public class SessionsPerPointResponse {
	@JsonProperty("Point")
	private final String pointID;

	@JsonProperty("RequestTimestamp")
	private final String requestTimestamp;

	@JsonProperty("PeriodFrom")
	private final String periodFrom;

	@JsonProperty("PeriodTo")
	private final String periodTo;

	@JsonProperty("NumberOfChargingSessions")
	private final Long numberOfChargingSessions;

	@JsonProperty("ChargingSessionsList")
	private final ChargingSession[] chargingSessions;

	public SessionsPerPointResponse(String pointID, String requestTimestamp, String periodFrom, String periodTo,
			Long numberOfChargingSessions, List<Session> sessions) {
		this.pointID = pointID;
		this.requestTimestamp = requestTimestamp;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.numberOfChargingSessions = numberOfChargingSessions;
		
		int i = 1;
		List<ChargingSession> chargingSessionsList = new ArrayList<>();

		for(Session session: sessions){
			ChargingSession chargingSession = new ChargingSession(Integer.valueOf(i), 
				session.getId().toString(),
				session.getStartedOn().toString(),
				session.getFinishedOn().toString(), 
				session.getProtocol(), 
				Float.valueOf(session.getEnergyDelivered()), 
				session.getPayment(), 
				session.getCar().getType());
			
			chargingSessionsList.add(chargingSession);

			i++;
		}

		this.chargingSessions = chargingSessionsList.toArray(new ChargingSession[0]);
	}


	public String getPointID() {
		return pointID;
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

	public Long getNumberOfChargingSessions() {
		return numberOfChargingSessions;
	}

	public ChargingSession[] getChargingSessions() {
		return chargingSessions;
	}

	@Override
	public String toString() {
		return "SessionsPerPointResponse [chargingSessions=" + Arrays.toString(chargingSessions)
				+ ", numberOfChargingSessions=" + numberOfChargingSessions + ", periodFrom=" + periodFrom
				+ ", periodTo=" + periodTo + ", pointID=" + pointID + ", requestTimestamp=" + requestTimestamp + "]";
	}
}