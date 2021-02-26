package ntua.softeng28.evcharge.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarsByBrandResponse {
    @JsonProperty("Id")
	private final String id;

	@JsonProperty("Type")
	private final String type;

    @JsonProperty("Model")
	private final String model;

	@JsonProperty("ReleaseYear")
	private final Integer releaseYear;

	@JsonProperty("Variant")
	private final String variant;

	@JsonProperty("UsableBatterySize")
	private final Float usableBatterySize;

	public CarsByBrandResponse(String id, String type, String model, Integer releaseYear, String variant,
			Float usableBatterySize) {
		this.id = id;
		this.type = type;
		this.model = model;
		this.releaseYear = releaseYear;
		this.variant = variant;
		this.usableBatterySize = usableBatterySize;
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getModel() {
		return model;
	}

	public Integer getReleaseYear() {
		return releaseYear;
	}

	public String getVariant() {
		return variant;
	}

	public Float getUsableBatterySize() {
		return usableBatterySize;
	}

	@Override
	public String toString() {
		return "CarsByBrandResponse [id=" + id + ", model=" + model + ", releaseYear=" + releaseYear + ", type=" + type
				+ ", usableBatterySize=" + usableBatterySize + ", variant=" + variant + "]";
	}

}
