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

	public ChargingPoint(Operator operator) {
		super();
		this.operator = operator;
	}

	public ChargingPoint(Long id, Operator operator) {
		super();
		this.id = id;
		this.operator = operator;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChargingPoint other = (ChargingPoint) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		return true;
	}

}
