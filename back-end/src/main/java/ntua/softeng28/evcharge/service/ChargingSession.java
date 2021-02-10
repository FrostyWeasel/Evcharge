package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargingSession {
    @JsonProperty("SessionIndex")           //Integer Α/Α γεγονότος φόρτισης στην περίοδο (1, 2, 3, ...)
    private final Integer sessionIndex;

    @JsonProperty("SessionID")              //String Το ID του γεγονότος φόρτισης
    private final String sessionID;

    @JsonProperty("StartedOn")              //String Η χρονική στιγμή εκκίνησης της φόρτισης
    private final String startedOn;

    @JsonProperty("FinishedOn")             //String Η χρονική στιγμή ολοκλήρωσης της φόρτισης
    private final String finishedOn;

    @JsonProperty("Protocol")               //String Το ακολουθούμενο πρωτόκολλο φόρτισης
    private final String protocol;

    @JsonProperty("EnergyDelivered")        //Float Η ενέργεια που μεταφέρθηκε σε KWh
    private final Float energyDelivered;

    @JsonProperty("Payment")                //String Ο τρόπος πληρωμής
    private final String payment;

    @JsonProperty("VehicleType")            //String Ο τύπος του οχήματος
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