package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargingSession {
    @JsonProperty("SessionIndex")           
    private final Integer sessionIndex;

    @JsonProperty("SessionID")              
    private final String sessionID;

    @JsonProperty("StartedOn")              
    private final String startedOn;

    @JsonProperty("FinishedOn")             
    private final String finishedOn;

    @JsonProperty("Protocol")               
    private final String protocol;

    @JsonProperty("EnergyDelivered")        
    private final Float energyDelivered;

    @JsonProperty("Payment")                
    private final String payment;

    @JsonProperty("VehicleType")            
    private final String vehicleType;

	public ChargingSession(Integer sessionIndex, String sessionID, String startedOn, String finishedOn, String protocol,
			Float energyDelivered, String payment, String vehicleType) {
		this.sessionIndex = sessionIndex;
		this.sessionID = sessionID;
		this.startedOn = startedOn;
		this.finishedOn = finishedOn;
		this.protocol = protocol;
		this.energyDelivered = energyDelivered;
		this.payment = payment;
		this.vehicleType = vehicleType;
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
		return "ChargingSession [energyDelivered=" + energyDelivered + ", finishedOn=" + finishedOn + ", payment="
				+ payment + ", protocol=" + protocol + ", sessionID=" + sessionID + ", sessionIndex=" + sessionIndex
				+ ", startedOn=" + startedOn + ", vehicleType=" + vehicleType + "]";
	}

}