package ntua.softeng28.evcharge.charging_station;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ntua.softeng28.evcharge.operator.Operator;
import ntua.softeng28.evcharge.charging_point.ChargingPoint;

@Entity
public class ChargingStation {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

	@ManyToOne
	private Operator operator;

	@OneToOne
	private Address address;

	@OneToMany
	private Set<ChargingPoint> chargingPoints;

	public ChargingStation() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Set<ChargingPoint> getChargingPoints() {
		return chargingPoints;
	}

	public void setChargingPoints(Set<ChargingPoint> chargingPoints) {
		this.chargingPoints = chargingPoints;
	}

	@Override
	public String toString() {
		return "ChargingStation [address=" + address + ", chargingPoints=" + chargingPoints + ", id=" + id
				+ ", operator=" + operator + "]";
	}

}
