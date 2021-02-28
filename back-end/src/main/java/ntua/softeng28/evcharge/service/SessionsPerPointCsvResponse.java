package ntua.softeng28.evcharge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByPosition;

import ntua.softeng28.evcharge.session.Session;

public class SessionsPerPointCsvResponse {
    @CsvBindByPosition(position = 0)
	@JsonProperty("Point")
	private final String pointID;

    @CsvBindByPosition(position = 1)
	@JsonProperty("PointOperator")
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
	@JsonProperty("NumberOfChargingSessions")
	private final Long numberOfChargingSessions;

    @CsvBindByPosition(position = 6)
    @JsonProperty("SessionIndex")           
    private final Integer sessionIndex;

    @CsvBindByPosition(position = 7)
    @JsonProperty("SessionID")              
    private final String sessionID;

    @CsvBindByPosition(position = 8)
    @JsonProperty("StartedOn")              
    private final String startedOn;

    @CsvBindByPosition(position = 9)
    @JsonProperty("FinishedOn")             
    private final String finishedOn;

    @CsvBindByPosition(position = 10)
    @JsonProperty("Protocol")               
    private final String protocol;

    @CsvBindByPosition(position = 11)
    @JsonProperty("EnergyDelivered")        
    private final Float energyDelivered;

    @CsvBindByPosition(position = 12)
    @JsonProperty("Payment")                
    private final String payment;

    @CsvBindByPosition(position = 13)
    @JsonProperty("VehicleType")            
    private final String vehicleType;

	public SessionsPerPointCsvResponse(String pointID, String operator, String requestTimestamp, String periodFrom,
			String periodTo, Long numberOfChargingSessions, Integer sessionIndex, String sessionID, String startedOn,
			String finishedOn, String protocol, Float energyDelivered, String payment, String vehicleType) {
		this.pointID = pointID;
		this.operator = operator;
		this.requestTimestamp = requestTimestamp;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.numberOfChargingSessions = numberOfChargingSessions;
		this.sessionIndex = sessionIndex;
		this.sessionID = sessionID;
		this.startedOn = startedOn;
		this.finishedOn = finishedOn;
		this.protocol = protocol;
		this.energyDelivered = energyDelivered;
		this.payment = payment;
		this.vehicleType = vehicleType;
	}

	public String getPointID() {
		return pointID;
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

	public Long getNumberOfChargingSessions() {
		return numberOfChargingSessions;
	}

	public Integer getSessionIndex() {
		return sessionIndex;
	}

	public String getSessionID() {
		return sessionID;
	}

	public String getStartedOn() {
		return startedOn;
	}

	public String getFinishedOn() {
		return finishedOn;
	}

	public String getProtocol() {
		return protocol;
	}

	public Float getEnergyDelivered() {
		return energyDelivered;
	}

	public String getPayment() {
		return payment;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	@Override
	public String toString() {
		return "SessionsPerPointResponse [energyDelivered=" + energyDelivered + ", finishedOn=" + finishedOn
				+ ", numberOfChargingSessions=" + numberOfChargingSessions + ", operator=" + operator + ", payment="
				+ payment + ", periodFrom=" + periodFrom + ", periodTo=" + periodTo + ", pointID=" + pointID
				+ ", protocol=" + protocol + ", requestTimestamp=" + requestTimestamp + ", sessionID=" + sessionID
				+ ", sessionIndex=" + sessionIndex + ", startedOn=" + startedOn + ", vehicleType=" + vehicleType + "]";
	}

	
}