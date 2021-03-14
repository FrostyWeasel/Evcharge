package ntua.softeng28.evcharge.session;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ntua.softeng28.evcharge.car.Car;
import ntua.softeng28.evcharge.charging_point.ChargingPoint;
import ntua.softeng28.evcharge.charging_station.ChargingStation;
import ntua.softeng28.evcharge.energy_provider.EnergyProvider;
import ntua.softeng28.evcharge.user.User;

@Entity
public class Session {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	// private String type;
	// private String description;
	private Timestamp startedOn;
	private Timestamp finishedOn;
	private String protocol;
	private String payment;
	private Float energyDelivered;
	private Float cost;


	@ManyToOne(optional = false)
	private Car car;

	@ManyToOne(optional = false)
	private ChargingPoint chargingPoint;

	@ManyToOne(optional = false)
	private EnergyProvider energyProvider;

	@ManyToOne(optional = false)
	private User user;

	public Session() {

	}

    public Session(Long id, Timestamp startedOn, Timestamp finishedOn, String protocol, String payment,
            Float energyDelivered, Float cost, Car car, ChargingPoint chargingPoint, EnergyProvider energyProvider,
            User user) {
        this.id = id;
        this.startedOn = startedOn;
        this.finishedOn = finishedOn;
        this.protocol = protocol;
        this.payment = payment;
        this.energyDelivered = energyDelivered;
        this.cost = cost;
        this.car = car;
        this.chargingPoint = chargingPoint;
        this.energyProvider = energyProvider;
        this.user = user;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
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

	public EnergyProvider getEnergyProvider() {
		return energyProvider;
	}

	public void setEnergyProvider(EnergyProvider energyProvider) {
		this.energyProvider = energyProvider;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Session [car=" + car + ", chargingPoint=" + chargingPoint + ", cost=" + cost + ", energyDelivered="
				+ energyDelivered + ", energyProvider=" + energyProvider + ", finishedOn=" + finishedOn + ", id=" + id
				+ ", payment=" + payment + ", protocol=" + protocol + ", startedOn=" + startedOn + ", user=" + user
				+ "]";
	}
}