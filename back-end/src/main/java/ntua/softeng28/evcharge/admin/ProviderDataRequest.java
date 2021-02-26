package ntua.softeng28.evcharge.admin;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProviderDataRequest {
    @JsonProperty("Providers")
    ProviderData[] providerData;

	public ProviderDataRequest(){

	}
	
	public ProviderDataRequest(ProviderData[] providerData) {
		this.providerData = providerData;
	}

	public ProviderData[] getProviderData() {
		return providerData;
	}

	public void setProviderData(ProviderData[] providerData) {
		this.providerData = providerData;
	}

	@Override
	public String toString() {
		return "ProviderDataRequest [providerData=" + Arrays.toString(providerData) + "]";
	}


	
}
