package ntua.softeng28.evcharge.payment;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ntua.softeng28.evcharge.car.Car;
import ntua.softeng28.evcharge.charging_point.ChargingPoint;
import ntua.softeng28.evcharge.charging_station.ChargingStation;
import ntua.softeng28.evcharge.user.User;

@Entity
public class Payment {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private int amount;
	private Timestamp timestamp;

	@ManyToOne
	private User user;

	@ManyToOne
	private Car car;

	@ManyToOne
	private ChargingStation chargingStation;

	@ManyToOne
	private ChargingPoint chargingPoint;

	public Payment() {
		super();
	}

	public Payment(int amount, Timestamp timestamp, User user, Car car,
			ChargingStation chargingStation, ChargingPoint chargingPoint) {
		this.amount = amount;
		this.timestamp = timestamp;
		this.user = user;
		this.car = car;
		this.chargingStation = chargingStation;
		this.chargingPoint = chargingPoint;
	}

	public Long getid() {
		return id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public ChargingStation getChargingstation() {
		return chargingStation;
	}

	public void setChargingstation(ChargingStation chargingStation) {
		this.chargingStation = chargingStation;
	}

	public ChargingPoint getChargingpoint() {
		return chargingPoint;
	}

	public void setChargingpoint(ChargingPoint chargingPoint) {
		this.chargingPoint = chargingPoint;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount  + ", timestamp=" + timestamp + ", user=" + user
				+ ", car=" + car + ", chargingstation=" + chargingStation + ", chargingpoint=" + chargingPoint + "]";
	}

}
