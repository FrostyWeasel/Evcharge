package ntua.softeng28.evcharge.session;

import java.sql.Timestamp;

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
	private Timestamp started_on;
	private Timestamp finished_on;
	private String protocol;
	private long energy_delivered;


	@ManyToOne
	private Car car;

	@ManyToOne
	private ChargingStation chargingStation;

	@ManyToOne
	private ChargingPoint chargingPoint;

	public Session() {
	}

	public Session(Long id, String type, String description, Timestamp started_on, Timestamp finished_on,
			String protocol, long energy_delivered, Car car, ChargingStation chargingStation,
			ChargingPoint chargingPoint) {
		this.id = id;
		this.type = type;
		this.description = description;
		this.started_on = started_on;
		this.finished_on = finished_on;
		this.protocol = protocol;
		this.energy_delivered = energy_delivered;
		this.car = car;
		this.chargingStation = chargingStation;
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

	public Timestamp getStarted_on() {
		return started_on;
	}

	public void setStarted_on(Timestamp started_on) {
		this.started_on = started_on;
	}

	public Timestamp getFinished_on() {
		return finished_on;
	}

	public void setFinished_on(Timestamp finished_on) {
		this.finished_on = finished_on;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public long getEnergy_delivered() {
		return energy_delivered;
	}

	public void setEnergy_delivered(long energy_delivered) {
		this.energy_delivered = energy_delivered;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ChargingStation getChargingStation() {
		return chargingStation;
	}

	public void setChargingStation(ChargingStation chargingStation) {
		this.chargingStation = chargingStation;
	}

	public ChargingPoint getChargingPoint() {
		return chargingPoint;
	}

	public void setChargingPoint(ChargingPoint chargingPoint) {
		this.chargingPoint = chargingPoint;
	}

	

}
