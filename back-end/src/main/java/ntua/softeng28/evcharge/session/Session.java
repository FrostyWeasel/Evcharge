package ntua.softeng28.evcharge.session;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ntua.softeng28.evcharge.car.Car;
import ntua.softeng28.evcharge.charging_point.ChargingPoint;
import ntua.softeng28.evcharge.charging_station.ChargingStation;

@Entity
public class Session {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private String type;
	private String description;
	private Timestamp startedOn;
	private Timestamp finishedOn;
	private String protocol;
	private String payment;
	private Float energyDelivered;


	@ManyToOne
	private Car car;

	@ManyToOne
	private ChargingPoint chargingPoint;

	public Session() {
	}

	public Session(Long id, String type, String description, Timestamp startedOn, Timestamp finishedOn, String protocol,
			String payment, Float energyDelivered, Car car, ChargingPoint chargingPoint) {
		this.id = id;
		this.type = type;
		this.description = description;
		this.startedOn = startedOn;
		this.finishedOn = finishedOn;
		this.protocol = protocol;
		this.payment = payment;
		this.energyDelivered = energyDelivered;
		this.car = car;
		this.chargingPoint = chargingPoint;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Float getEnergyDelivered() {
		return energyDelivered;
	}

	public void setEnergyDelivered(Float energyDelivered) {
		this.energyDelivered = energyDelivered;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ChargingPoint getChargingPoint() {
		return chargingPoint;
	}

	public void setChargingPoint(ChargingPoint chargingPoint) {
		this.chargingPoint = chargingPoint;
	}

	@Override
	public String toString() {
		return "Session [car=" + car + ", chargingPoint=" + chargingPoint + ", description=" + description
				+ ", energy_delivered=" + energyDelivered + ", finishedOn=" + finishedOn + ", id=" + id + ", payment="
				+ payment + ", protocol=" + protocol + ", startedOn=" + startedOn + ", type=" + type + "]";
	}

}