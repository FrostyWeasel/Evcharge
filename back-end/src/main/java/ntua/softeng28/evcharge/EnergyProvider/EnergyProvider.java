package ntua.softeng28.evcharge.EnergyProvider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import ntua.softeng28.evcharge.session.Session;

@Entity
public class EnergyProvider {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

	private String BrandName;

	private int Low_Price;
	private int Mid_Price;
	private int High_Price;

	private int LowtoMid_Limit;
	private int MidtoHigh_Limit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	public int getLow_Price() {
		return Low_Price;
	}

	public void setLow_Price(int low_Price) {
		Low_Price = low_Price;
	}

	public int getMid_Price() {
		return Mid_Price;
	}

	public void setMid_Price(int mid_Price) {
		Mid_Price = mid_Price;
	}

	public int getHigh_Price() {
		return High_Price;
	}

	public void setHigh_Price(int high_Price) {
		High_Price = high_Price;
	}

	public int getLowtoMid_Limit() {
		return LowtoMid_Limit;
	}

	public void setLowtoMid_Limit(int lowtoMid_Limit) {
		LowtoMid_Limit = lowtoMid_Limit;
	}

	public int getMidtoHigh_Limit() {
		return MidtoHigh_Limit;
	}

	public void setMidtoHigh_Limit(int midtoHigh_Limit) {
		MidtoHigh_Limit = midtoHigh_Limit;
	}

}
