package ntua.softeng28.evcharge.admin;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProviderData {
    @JsonProperty("Name")
    private String brandName;

    @JsonProperty("Low_Price")
    private Float lowPrice;

    @JsonProperty("Mid_Price")
    private Float midPrice;

    @JsonProperty("High_Price")
    private Float highPrice;

    @JsonProperty("LowtoMid_Limit")
    private Float lowtoMidLimit;

    @JsonProperty("MidtoHigh_Limit")
    private Float midtoHighLimit;

	public ProviderData(){

	}

	public ProviderData(String brandName, Float lowPrice, Float midPrice, Float highPrice, Float lowtoMidLimit,
			Float midtoHighLimit) {
		this.brandName = brandName;
		this.lowPrice = lowPrice;
		this.midPrice = midPrice;
		this.highPrice = highPrice;
		this.lowtoMidLimit = lowtoMidLimit;
		this.midtoHighLimit = midtoHighLimit;
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
		this.lowPrice = lowPrice;
	}

	public Float getMidPrice() {
		return midPrice;
	}

	public void setMidPrice(Float midPrice) {
		this.midPrice = midPrice;
	}

	public Float getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Float highPrice) {
		this.highPrice = highPrice;
	}

	public Float getLowtoMidLimit() {
		return lowtoMidLimit;
	}

	public void setLowtoMidLimit(Float lowtoMidLimit) {
		this.lowtoMidLimit = lowtoMidLimit;
	}

	public Float getMidtoHighLimit() {
		return midtoHighLimit;
	}

	public void setMidtoHighLimit(Float midtoHighLimit) {
		this.midtoHighLimit = midtoHighLimit;
	}

	@Override
	public String toString() {
		return "ProviderDataRequest [brandName=" + brandName + ", highPrice=" + highPrice + ", lowPrice=" + lowPrice
				+ ", lowtoMidLimit=" + lowtoMidLimit + ", midPrice=" + midPrice + ", midtoHighLimit=" + midtoHighLimit
				+ "]";
	}
}
