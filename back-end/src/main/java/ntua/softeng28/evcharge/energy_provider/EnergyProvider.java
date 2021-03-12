package ntua.softeng28.evcharge.energy_provider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

import ntua.softeng28.evcharge.session.Session;

@Entity
public class EnergyProvider {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

	private String brandName;

	@Column(nullable = false)
	private Float lowPrice;

	@Column(nullable = false)
	private Float midPrice;

	@Column(nullable = false)
	private Float highPrice;

	@Column(nullable = false)
	private Float lowtoMidLimit;

	@Column(nullable = false)
	private Float midtoHighLimit;

	public EnergyProvider() {
	}

	public EnergyProvider(Long id, String brandName, Float lowPrice, Float midPrice, Float highPrice,
			Float lowtoMidLimit, Float midtoHighLimit) {
		this.id = id;
		this.brandName = brandName;
		this.lowPrice = lowPrice;
		this.midPrice = midPrice;
		this.highPrice = highPrice;
		this.lowtoMidLimit = lowtoMidLimit;
		this.midtoHighLimit = midtoHighLimit;
	}

	public EnergyProvider(String brandName, Float lowPrice, Float midPrice, Float highPrice, Float lowtoMidLimit,
			Float midtoHighLimit) {
		super();
		this.brandName = brandName;
		this.lowPrice = lowPrice;
		this.midPrice = midPrice;
		this.highPrice = highPrice;
		this.lowtoMidLimit = lowtoMidLimit;
		this.midtoHighLimit = midtoHighLimit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Float getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Float lowPrice) {
		if (lowPrice == null)
			this.lowPrice = Float.valueOf(0);
		else
			this.lowPrice = lowPrice;
	}

	public Float getMidPrice() {
		return midPrice;
	}

	public void setMidPrice(Float midPrice) {
		if (midPrice == null)
			this.midPrice = Float.valueOf(0);
		else
			this.midPrice = midPrice;
	}

	public Float getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Float highPrice) {
		if (highPrice == null)
			this.highPrice = Float.valueOf(0);
		else
			this.highPrice = highPrice;
	}

	public Float getLowtoMidLimit() {
		return lowtoMidLimit;
	}

	public void setLowtoMidLimit(Float lowtoMidLimit) {
		if (lowtoMidLimit == null)
			this.lowtoMidLimit = Float.valueOf(0);
		else
			this.lowtoMidLimit = lowtoMidLimit;
	}

	public Float getMidtoHighLimit() {
		return midtoHighLimit;
	}

	public void setMidtoHighLimit(Float midtoHighLimit) {
		if (midtoHighLimit == null)
			this.midtoHighLimit = Float.valueOf(0);
		else
			this.midtoHighLimit = midtoHighLimit;
	}

	@Override
	public String toString() {
		return "EnergyProvider [brandName=" + brandName + ", highPrice=" + highPrice + ", id=" + id + ", lowPrice="
				+ lowPrice + ", lowtoMidLimit=" + lowtoMidLimit + ", midPrice=" + midPrice + ", midtoHighLimit="
				+ midtoHighLimit + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((highPrice == null) ? 0 : highPrice.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lowPrice == null) ? 0 : lowPrice.hashCode());
		result = prime * result + ((lowtoMidLimit == null) ? 0 : lowtoMidLimit.hashCode());
		result = prime * result + ((midPrice == null) ? 0 : midPrice.hashCode());
		result = prime * result + ((midtoHighLimit == null) ? 0 : midtoHighLimit.hashCode());
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
		EnergyProvider other = (EnergyProvider) obj;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (highPrice == null) {
			if (other.highPrice != null)
				return false;
		} else if (!highPrice.equals(other.highPrice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lowPrice == null) {
			if (other.lowPrice != null)
				return false;
		} else if (!lowPrice.equals(other.lowPrice))
			return false;
		if (lowtoMidLimit == null) {
			if (other.lowtoMidLimit != null)
				return false;
		} else if (!lowtoMidLimit.equals(other.lowtoMidLimit))
			return false;
		if (midPrice == null) {
			if (other.midPrice != null)
				return false;
		} else if (!midPrice.equals(other.midPrice))
			return false;
		if (midtoHighLimit == null) {
			if (other.midtoHighLimit != null)
				return false;
		} else if (!midtoHighLimit.equals(other.midtoHighLimit))
			return false;
		return true;
	}

}