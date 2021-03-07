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
		if(lowPrice == null)
			this.lowPrice = Float.valueOf(0);
		else
			this.lowPrice = lowPrice;
	}

	public Float getMidPrice() {
		return midPrice;
	}

	public void setMidPrice(Float midPrice) {
		if(midPrice == null)
			this.midPrice = Float.valueOf(0);
		else
			this.midPrice = midPrice;
	}

	public Float getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Float highPrice) {
		if(highPrice == null)
			this.highPrice = Float.valueOf(0);
		else
			this.highPrice = highPrice;
	}

	public Float getLowtoMidLimit() {
		return lowtoMidLimit;
	}

	public void setLowtoMidLimit(Float lowtoMidLimit) {
		if(lowtoMidLimit == null)
			this.lowtoMidLimit = Float.valueOf(0);
		else
			this.lowtoMidLimit = lowtoMidLimit;
	}

	public Float getMidtoHighLimit() {
		return midtoHighLimit;
	}

	public void setMidtoHighLimit(Float midtoHighLimit) {
		if(midtoHighLimit == null)
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
}