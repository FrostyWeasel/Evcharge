package ntua.softeng28.evcharge.session;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionDataRequest {
    @JsonProperty("StartedOn")
	private final Timestamp startedOn;

	@JsonProperty("FinishedOn")
	private final Timestamp finishedOn;

	@JsonProperty("Protocol")
	private final String protocol;

	@JsonProperty("Payment")
	private final String payment;

	@JsonProperty("Cost")
	private final Float cost;

	@JsonProperty("EnergyDelivered")
	private final Float energyDelivered;

    @JsonProperty("VehicleID")
	private final String vehicleID;

	@JsonProperty("ChargingPointID")
	private final Long chargingPointID;

    @JsonProperty("EnergyProviderID")
	private final Long energyProviderID;

	public SessionDataRequest(Timestamp startedOn, Timestamp finishedOn, String protocol, String payment, Float cost,
			Float energyDelivered, String vehicleID, Long chargingPointID, Long energyProviderID) {
		this.startedOn = startedOn;
		this.finishedOn = finishedOn;
		this.protocol = protocol;
		this.payment = payment;
		this.cost = cost;
		this.energyDelivered = energyDelivered;
		this.vehicleID = vehicleID;
		this.chargingPointID = chargingPointID;
		this.energyProviderID = energyProviderID;
	}

	public Timestamp getStartedOn() {
		return startedOn;
	}

	public Timestamp getFinishedOn() {
		return finishedOn;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getPayment() {
		return payment;
	}

	public Float getCost() {
		return cost;
	}

	public Float getEnergyDelivered() {
		return energyDelivered;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public Long getChargingPointID() {
		return chargingPointID;
	}

	public Long getEnergyProviderID() {
		return energyProviderID;
	}

	@Override
	public String toString() {
		return "SessionDataRequest [chargingPointID=" + chargingPointID + ", cost=" + cost + ", energyDelivered="
				+ energyDelivered + ", energyProviderID=" + energyProviderID + ", finishedOn=" + finishedOn
				+ ", payment=" + payment + ", protocol=" + protocol + ", startedOn=" + startedOn + ", vehicleID="
				+ vehicleID + "]";
	}
}