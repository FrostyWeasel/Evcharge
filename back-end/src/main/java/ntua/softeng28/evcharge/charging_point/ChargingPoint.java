package ntua.softeng28.evcharge.charging_point;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ntua.softeng28.evcharge.operator.Operator;
import ntua.softeng28.evcharge.charging_station.ChargingStation;
import ntua.softeng28.evcharge.session.Session;

@Entity 
public class ChargingPoint {
	
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

	@ManyToOne
	private Operator operator;

	
	public ChargingPoint() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "ChargingPoint [id=" + id + ", operator=" + operator + "]";
	}
}

