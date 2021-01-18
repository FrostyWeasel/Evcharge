package ntua.softeng28.evcharge.charging_point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ntua.softeng28.evcharge.charging_station.ChargingStation;

@Entity 
public class ChargingPoint {
	
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private String location;
	private String operator;
	
	@ManyToOne
	private ChargingStation chargingstation;
	
	public ChargingPoint() {
	}

	public ChargingPoint(Long id, String location, String operator, ChargingStation chargingstation) {
		this.id = id;
		this.location = location;
		this.operator = operator;
		this.chargingstation = chargingstation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ChargingStation getChargingstation() {
		return chargingstation;
	}

	public void setChargingstation(ChargingStation chargingstation) {
		this.chargingstation = chargingstation;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "ChargingPoint [id=" + id + ", location=" + location + ", chargingstation=" + chargingstation
				+ "]";
	}
	
}

