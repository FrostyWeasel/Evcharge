package ntua.softeng28.evcharge.session;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionDataRequest {
    @JsonProperty("StartedOn")
	private Timestamp startedOn;

	@JsonProperty("FinishedOn")
	private Timestamp finishedOn;

	@JsonProperty("Protocol")
	private String protocol;

	@JsonProperty("Payment")
	private String payment;

	@JsonProperty("Cost")
	private Float cost;

	@JsonProperty("EnergyDelivered")
	private Float energyDelivered;

    @JsonProperty("VehicleID")
	private String vehicleID;

	@JsonProperty("ChargingPointID")
	private Long chargingPointID;

    @JsonProperty("EnergyProviderID")
	private Long energyProviderID;

	@JsonProperty("Username")
	private String username;

	public SessionDataRequest(){

	}

    public SessionDataRequest(Timestamp startedOn, Timestamp finishedOn, String protocol, String payment, Float cost,
            Float energyDelivered, String vehicleID, Long chargingPointID, Long energyProviderID, String username) {
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
        this.protocol = protocol;
        this.payment = payment;
        this.cost = cost;
        this.energyDelivered = energyDelivered;
        this.vehicleID = vehicleID;
        this.chargingPointID = chargingPointID;
        this.energyProviderID = energyProviderID;
        this.username = username;
    }

    public Timestamp getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Timestamp startedOn) {
        this.startedOn = startedOn;
    }

    public Timestamp getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(Timestamp finishedOn) {
        this.finishedOn = finishedOn;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getEnergyDelivered() {
        return energyDelivered;
    }

    public void setEnergyDelivered(Float energyDelivered) {
        this.energyDelivered = energyDelivered;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Long getChargingPointID() {
        return chargingPointID;
    }

    public void setChargingPointID(Long chargingPointID) {
        this.chargingPointID = chargingPointID;
    }

    public Long getEnergyProviderID() {
        return energyProviderID;
    }

    public void setEnergyProviderID(Long energyProviderID) {
        this.energyProviderID = energyProviderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String toString() {
		return "SessionDataRequest [chargingPointID=" + chargingPointID + ", cost=" + cost + ", energyDelivered="
				+ energyDelivered + ", energyProviderID=" + energyProviderID + ", finishedOn=" + finishedOn
				+ ", payment=" + payment + ", protocol=" + protocol + ", startedOn=" + startedOn + ", username="
				+ username + ", vehicleID=" + vehicleID + "]";
	}	
}