package ntua.softeng28.evcharge.charging_station;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChargingStation {
	
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	private String location;
	
	public ChargingStation() {
	}

	public ChargingStation(String location) {
		this.location = location;
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

	@Override
	public String toString() {
		return "ChargingStation [id=" + id + ", location=" + location + "]";
	}
	
}
